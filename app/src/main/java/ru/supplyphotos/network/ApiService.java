package ru.supplyphotos.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import ru.supplyphotos.data.answers.category.Category;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.Services;
import ru.supplyphotos.data.answers.start_login.StartToken;

import static ru.supplyphotos.constants.Constants.API_URL;

/**
 * @author libgo on 12.01.2018.
 */

public interface ApiService {

    @GET("study")
    Observable<Manual> getManuals();

    @GET("register")
    Observable<StartToken> getDeviceToken();

    @GET("category")
    Observable<Category> getCategory();

    @GET("service")
    Observable<Services> getService(@Query("category_id") Integer id_service);

    @Multipart
    @POST ("20180312T134901.935.utd.b5fq134ij0bgjwnqu4rfwvpby-k4j.1145146")
    Call<ResponseBody> sendPhoto (@Part MultipartBody.Part image);
    

    Retrofit sendPhotoRetrofit = new Retrofit.Builder()
            .baseUrl("https://uploader4j.disk.yandex.net:443/upload-target/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
   

    


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)

            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            //Для всех запросов используется шедулер созданный выше.
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
//http://photo.it33.ru/api/register

/**
 * register - стартовая регистрация дивайса
 * study - для экрана обучения
 * category - категории услуг
 */