package ru.supplyphotos.di.modules;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.supplyphotos.App;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.data.storage.StorageManager;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;

import static ru.supplyphotos.constants.Constants.APP_SETTINGS;

/**
 * @author Libgo on 17.01.2018.
 */
@Module
@Reusable
public class SettingModule {


    @Provides
    @Reusable
    ResourceManager resourceManager(Resources resources){
        return new ResourceManager(resources);
    }

    @Provides
    @Reusable
    StorageManager storageManager(ContentResolver contentResolver){
        return new StorageManager(contentResolver);
    }
    
    @Provides
    @Reusable
    Resources resources(Context context){
        return context.getResources();
    }

    @Provides
    @Reusable
    ContentResolver contentResolver(Context context){
        return context.getContentResolver();
    }


    @Provides
    @Reusable
    SettingsHelper settingsHelper(SharedPreferences sharedPreferences){
        return new SettingsHelper(sharedPreferences);
    }


    @Provides
    @Reusable
    SharedPreferences sharedPreferences(Context context){
         return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

}
