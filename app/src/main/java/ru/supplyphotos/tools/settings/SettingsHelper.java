package ru.supplyphotos.tools.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ru.supplyphotos.App;
import ru.supplyphotos.data.answers.start_login.DeviceToken;

import static ru.supplyphotos.constants.Constants.APP_SETTINGS;
import static ru.supplyphotos.constants.Constants.DEVICE_FIRST_RUN;
import static ru.supplyphotos.constants.Constants.DEVICE_NAME;
import static ru.supplyphotos.constants.Constants.DEVICE_TOKEN;

/**
 * @author Libgo on 17.01.2018.
 */

public class SettingsHelper {

    private  SharedPreferences sharedPreferences;
    private  Context contextSettings = App.getAppComponent().getContext();

    public SettingsHelper() {
        sharedPreferences = contextSettings.getSharedPreferences(APP_SETTINGS, contextSettings.MODE_PRIVATE);
    }

    public  void setDeviceToken(DeviceToken deviceToken, Boolean first_run){
          SharedPreferences.Editor settingEditor = sharedPreferences.edit();
          settingEditor.putString(DEVICE_TOKEN, deviceToken.getToken());
          settingEditor.putString(DEVICE_NAME, deviceToken.getUsername());
          settingEditor.putBoolean(DEVICE_FIRST_RUN, first_run);
          settingEditor.apply();

        Log.d("DEVICE_T", sharedPreferences.getString(DEVICE_TOKEN, "0"));
        Log.d("DEVICE_N", sharedPreferences.getString(DEVICE_NAME, "0"));
    }

    public boolean getBooleanRun(){
        return sharedPreferences.getBoolean(DEVICE_FIRST_RUN, false);
    }

}
