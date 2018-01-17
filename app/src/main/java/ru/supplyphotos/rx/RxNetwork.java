package ru.supplyphotos.rx;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.supplyphotos.data.answers.manuals.Manual;
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

}
