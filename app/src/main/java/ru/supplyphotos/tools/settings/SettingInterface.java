package ru.supplyphotos.tools.settings;

import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.start_login.DeviceToken;
import ru.supplyphotos.data.settings.SelectedItemCategory;

/**
 * @author Libgo on 24.03.2018.
 */

public interface SettingInterface {

    void setDeviceToken(DeviceToken deviceToken);

    boolean getBooleanRun();

    void setFirstRun(Boolean b);

    void saveSelectedItemCategory(ItemCategory itemCategory);

    String getDeviceToken();

    SelectedItemCategory getSelectedItemCategory();

    SettingInterface getSettingsInterface();

    void saveSelectedServiceId(Integer service_id);


    Integer getSelectedServiceId();
}
