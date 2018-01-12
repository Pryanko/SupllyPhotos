package ru.supplyphotos.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import ru.supplyphotos.data.answers.Manual;

import static ru.supplyphotos.constants.Constants.API_URL;

/**
 * @autor user on 12.01.2018.
 */

public interface ApiService {

    @GET("bXSRRXEDpK?indent=2")
    Observable<List<Manual>> getManuals();


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

//   http://www.json-generator.com/api/json/get/bXSRRXEDpK?indent=2