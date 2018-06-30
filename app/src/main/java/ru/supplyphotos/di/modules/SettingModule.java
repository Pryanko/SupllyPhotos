package ru.supplyphotos.di.modules;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.data.storage.StorageManager;
import ru.supplyphotos.presentation.presenters.Representative;

/**
 * @author Libgo on 17.01.2018.
 */
@Module
@Reusable
public class SettingModule {

    @Reusable
    @Provides
    ResourceManager resourceManager(Resources resources) {
        return new ResourceManager(resources);
    }

    @Reusable
    @Provides
    StorageManager storageManager(){
        return storageManager();
    }

    @Reusable
    @Provides
    Resources resources(Context context) {
        return context.getResources();
    }

    @Provides
    @Reusable
    ContentResolver contentResolver(Context context) {
        return context.getContentResolver();
    }

    @Reusable
    @Provides
    Representative representative() {
        return new Representative();
    }

}
