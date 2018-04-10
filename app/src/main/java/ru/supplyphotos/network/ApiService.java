package ru.supplyphotos.network;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import ru.supplyphotos.data.answers.category.Category;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.Services;
import ru.supplyphotos.data.answers.start_login.StartToken;
import ru.supplyphotos.data.upload.cloud_upload_url.UploadUrl;
import ru.supplyphotos.data.upload.order_id.OrderId;
import ru.supplyphotos.data.upload.order_item_id.OrderItemId;
import ru.supplyphotos.data.upload.photo_id.PhotoId;

import static ru.supplyphotos.constants.Constants.API_URL;

/**
 * @author libgo on 12.01.2018.
 */

public interface ApiService {
    //region Get
    @GET("study")
    Observable<Manual> getManuals();

    @GET("register")
    Observable<StartToken> getDeviceToken();

    @GET("category")
    Observable<Category> getCategory();

    @GET("service")
    Observable<Services> getService(@Query("category_id") Integer id_service);

    @GET("service")
    Observable<Services> getDescriptionService(@Query("id") Integer id_service);
    //endregion

     //region Upload
     @GET("order-create")
     Flowable<OrderId> createOrder(@Query("access_token") String token);

    @GET("order-item-create")
    Flowable<OrderItemId> createOrderItem(@Query("access_token") String token,
                                        @Query("order_id") Integer order_id,
                                        @Query("service_id") Integer service_id);

    @GET("photo-add")
    Flowable<PhotoId> getPhotoId(@Query("access_token") String token,
                                   @Query("order_item_id") Integer order_item,
                                   @Query("filename") String filename,
                                 @Query("count") Integer count);

    @GET("photo-get-upload-url")
    Flowable<UploadUrl> getUploadUrl(@Query("access_token") String token,
                                       @Query("photo_id") Integer photo_id);

    @Multipart
    @POST()
    Flowable<ResponseBody> uploadPhoto(@Url String url, @Part MultipartBody.Part partImage);
    //endregion

    

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            //Для всех запросов используется шедулер созданный выше.
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();



}
//http://photo.it33.ru/api/register

/**
 * register - стартовая регистрация девайса
 * study - для экрана обучения
 * category - категории услуг
 * 28996a16bfb41400985f0088ba006b07
 */