package ru.supplyphotos.di.modules;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.App;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @author Libgo on 17.01.2018.
 */
@Module
@Singleton
public class SettingModule {

    @Provides
    @Singleton
     SettingsHelper settingsHelper(){
        return new SettingsHelper();
    }

}
