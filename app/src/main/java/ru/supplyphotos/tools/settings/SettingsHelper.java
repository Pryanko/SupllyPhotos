package ru.supplyphotos.tools.settings;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.start_login.DeviceToken;
import ru.supplyphotos.data.settings.SelectedItemCategory;

import static ru.supplyphotos.constants.Constants.DEFAULT_INT;
import static ru.supplyphotos.constants.Constants.DEFAULT_STRING;
import static ru.supplyphotos.constants.Constants.DEVICE_FIRST_RUN;
import static ru.supplyphotos.constants.Constants.DEVICE_NAME;
import static ru.supplyphotos.constants.Constants.DEVICE_TOKEN;
import static ru.supplyphotos.constants.Constants.IMAGE_SERVICE_HEAD;
import static ru.supplyphotos.constants.Constants.ITEM_CATEGORY_ID;
import static ru.supplyphotos.constants.Constants.ITEM_SERVICE_ID;

/**
 * @author Libgo on 17.01.2018.
 */
@Singleton
public class SettingsHelper implements SettingInterface {

    private SharedPreferences sharedPreferences;

    @Inject
    public SettingsHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void setDeviceToken(DeviceToken deviceToken) {
        SharedPreferences.Editor settingEditor = sharedPreferences.edit();
        settingEditor.putString(DEVICE_TOKEN, deviceToken.getToken());
        settingEditor.putString(DEVICE_NAME, deviceToken.getUsername());
        settingEditor.apply();

        Log.d("DEVICE_T", sharedPreferences.getString(DEVICE_TOKEN, DEFAULT_STRING));
        Log.d("DEVICE_N", sharedPreferences.getString(DEVICE_NAME, DEFAULT_STRING));
    }

    public boolean getBooleanRun() {
        return sharedPreferences
                .getBoolean(DEVICE_FIRST_RUN, false);
    }

    public void setFirstRun(Boolean b) {
        SharedPreferences.Editor settingEditor = sharedPreferences.edit();
        settingEditor.putBoolean(DEVICE_FIRST_RUN, b);
        settingEditor.apply();
    }

    public void saveSelectedItemCategory(ItemCategory itemCategory) {
        SharedPreferences.Editor settingEditor = sharedPreferences.edit();
        settingEditor.putInt(ITEM_CATEGORY_ID, itemCategory.getId());
        settingEditor.putString(IMAGE_SERVICE_HEAD, itemCategory.getImage480());
        settingEditor.apply();

    }

    private SelectedItemCategory ItemCategory() {
        SelectedItemCategory selectedItemCategory = new SelectedItemCategory();
        selectedItemCategory.setId(sharedPreferences.getInt(ITEM_CATEGORY_ID, DEFAULT_INT));
        selectedItemCategory.setImageHeadUrls(sharedPreferences
                .getString(IMAGE_SERVICE_HEAD, DEFAULT_STRING));
        return selectedItemCategory;
    }

    @Override
    public String getDeviceToken() {
        return sharedPreferences.getString(DEVICE_TOKEN, DEFAULT_STRING);
    }

    @Override
    public SelectedItemCategory getSelectedItemCategory() {
        return ItemCategory();
    }

    @Override
    public SettingInterface getSettingsInterface() {
        return this;
    }

    @Override
    public void saveSelectedServiceId(Integer service_id) {
        SharedPreferences.Editor settingEditor = sharedPreferences.edit();
        settingEditor.putInt(ITEM_SERVICE_ID, service_id);
        settingEditor.apply();
    }

    @Override
    public Integer getOrderItemId() {
        return sharedPreferences.getInt("order_item_id", 0);
    }

    @Override
    public void openOrderId(Boolean isOpenOrderItemId, Integer ordreItemId) {
        SharedPreferences.Editor settingEditor = sharedPreferences.edit();
        settingEditor.putBoolean("open_order_id", isOpenOrderItemId);
        settingEditor.putInt("order_item_id", ordreItemId);
        settingEditor.apply();
    }

    @Override
    public Integer getSelectedServiceId() {
        return sharedPreferences.getInt(ITEM_SERVICE_ID, 0);
    }

}
