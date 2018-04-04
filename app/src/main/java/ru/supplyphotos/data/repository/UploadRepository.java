package ru.supplyphotos.data.repository;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.supplyphotos.App;
import ru.supplyphotos.data.db.DataBaseSource;
import ru.supplyphotos.data.upload.PhotoIdFile;
import ru.supplyphotos.data.upload.cloud_upload_url.UploadUrl;
import ru.supplyphotos.data.upload.order_item_id.OrderItemId;
import ru.supplyphotos.network.ApiService;
import ru.supplyphotos.tools.mappers.Mappers;
import ru.supplyphotos.tools.settings.SettingInterface;

import static ru.supplyphotos.constants.Constants.API_URL;

/**
 * @author Libgo on 03.04.2018.
 */
public class UploadRepository implements BaseAppRepository.UploadRepository {

    private DataBaseSource dataBaseSource;
    private SettingInterface settingInterface;


    public UploadRepository() {
        this.dataBaseSource = App.getAppComponent().getDataBaseSource();
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
    }

    @Override
    public Flowable<OrderItemId> createOrderItem(){
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        return apiService.createOrder(settingInterface.getDeviceToken())
                .flatMap(orderId -> apiService.createOrderItem(settingInterface.getDeviceToken(),
                        orderId.getData().getOrderId(), settingInterface.getSelectedServiceId()));

    }

    @Override
    public Flowable<ResponseBody> startingUploadImage(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                //Для всех запросов используется шедулер созданный выше.
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();




        ApiService apiService = retrofit.create(ApiService.class);

                 Flowable<List<UploadUrl>> single = Flowable.just(Mappers.mapListImageSelected
                         (settingInterface.getOrderItemId(), dataBaseSource.getSelectedList()))
                .concatMap(Flowable::fromIterable)
                .concatMap(imageFile -> apiService.getPhotoId(settingInterface.getDeviceToken(),
                        imageFile.getItemOrderId(), imageFile.getNameImage(),
                        imageFile.getCountPrint()))
                .concatMap(photoId -> apiService.getUploadUrl(settingInterface.getDeviceToken(),
                        photoId.getData().getPhotoId()))
                .toList()
                         .toFlowable();

        Flowable<List<File>> listSingle = Flowable.just(Mappers.mapFileList(dataBaseSource.getSelectedList()));

         return Flowable.zip(single, listSingle, (BiFunction<List<UploadUrl>, List<File>, List<PhotoIdFile>>) Mappers::filterListZip)
                 .concatMap(Flowable::fromIterable)
                 .concatMap(photoIdFile -> {

                     RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), photoIdFile.getFile());
                     MultipartBody.Part body = MultipartBody.Part.createFormData("file", photoIdFile.getFile().getName(), requestFile);
                     Log.d("ZZZZZ", photoIdFile.getUploadUrl());
                     Log.d("ZZZZZ", photoIdFile.getFile().getName());
                     return apiService.uploadPhoto(photoIdFile.getUploadUrl(), body);
                 });


    }


    public Integer getMaxProgressBar(){
        return dataBaseSource.getSizeListSelectedItems();
    }








    
}
