package ru.supplyphotos.data.repository;

import android.util.Log;

import java.io.File;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import ru.supplyphotos.App;
import ru.supplyphotos.data.db.DataBaseSource;
import ru.supplyphotos.data.upload.PhotoIdFile;
import ru.supplyphotos.data.upload.cloud_upload_url.UploadUrl;
import ru.supplyphotos.network.ApiService;
import ru.supplyphotos.tools.mappers.Mappers;
import ru.supplyphotos.tools.settings.SettingInterface;

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
    public Flowable<ResponseBody> startingUploadImage(){

        ApiService apiService = ApiService.retrofit.create(ApiService.class);

        Flowable<List<UploadUrl>> single = apiService.createOrder(settingInterface.getDeviceToken())
                .concatMap(orderId -> apiService.createOrderItem(settingInterface.getDeviceToken(),
                        orderId.getData().getOrderId(), settingInterface.getSelectedServiceId()))
                .map(orderItemId -> Mappers.mapListImageSelected(orderItemId, dataBaseSource.getSelectedList()))
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










    
}
