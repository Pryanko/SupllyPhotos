package ru.supplyphotos.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import ru.supplyphotos.data.answers.category.Category;
import ru.supplyphotos.data.answers.manuals.Manual;
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