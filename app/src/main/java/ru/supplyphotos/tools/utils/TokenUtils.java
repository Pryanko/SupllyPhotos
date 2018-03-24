package ru.supplyphotos.tools.utils;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.Nullable;
import ru.supplyphotos.data.answers.start_login.DeviceToken;

/**
 * @author Libgo on 14.03.2018.
 */

public class TokenUtils {

    public static Observable<Boolean> getFlagValidToken(DeviceToken deviceToken){
        return Observable.just(validTokenDevice(deviceToken.getToken()));
    }

    private static Boolean validTokenDevice(@Nullable String token){
        return token != null;
    }

    //доработать валидность)

}
