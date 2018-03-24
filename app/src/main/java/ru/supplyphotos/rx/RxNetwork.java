package ru.supplyphotos.rx;

import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.answers.services.Services;
import ru.supplyphotos.data.answers.start_login.DeviceToken;
import ru.supplyphotos.network.ApiService;
import ru.supplyphotos.tools.mappers.Mappers;

/**
 * @author user on 17.01.2018.
 */

public class RxNetwork {

    private static ApiService apiService = ApiService.retrofit.create(ApiService.class);

    public static Observable<Manual> getGuides(){
        return apiService.getManuals()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<DeviceToken> getDeviceToken(){
        return apiService.getDeviceToken()
                .map(Mappers::mapDeviceToken)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<ItemCategory>> getListCategory(){
        return apiService.getCategory()
                .map(Mappers::mapListCategory)
                //.repeatWhen(objectObservable -> objectObservable.delay(5, TimeUnit.SECONDS))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<ItemService>> getListService(Integer sevice_id){
        return apiService.getService(sevice_id)
                .map(Mappers::mapListCategory)
                .observeOn(AndroidSchedulers.mainThread());
    }






}
