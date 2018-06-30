package ru.supplyphotos.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.supplyphotos.AppInitProvider;
import ru.supplyphotos.data.db.DataBaseSource;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.repository.UploadRepository;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.data.storage.StorageManager;
import ru.supplyphotos.di.modules.AppModule;
import ru.supplyphotos.di.modules.DataBaseModule;
import ru.supplyphotos.di.modules.NavigationModule;
import ru.supplyphotos.di.modules.RepositoryModule;
import ru.supplyphotos.di.modules.SettingModule;
import ru.supplyphotos.presentation.activities.base.ActivityModule;
import ru.supplyphotos.presentation.activities.head.HeadScreenComponent;
import ru.supplyphotos.presentation.activities.splash.SplashScreenComponent;
import ru.supplyphotos.presentation.mvp.core.MvpProcessor;
import ru.supplyphotos.presentation.presenters.Representative;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * @autor user on 12.01.2018.
 */
@Singleton
@Component(modules = {NavigationModule.class, SettingModule.class, AppModule.class,
        RepositoryModule.class, DataBaseModule.class})

public interface AppComponent {

    void inject(AppInitProvider appInitProvider);

    MvpProcessor mvpProcessor();

    SplashScreenComponent splashScreenComponent();

    HeadScreenComponent headScreenComponent();

    //
    DataBaseSource getDataBaseSource();

    AppRepository getAppRepository();

    UploadRepository getUploadRepository();

    Router getRouter();

    NavigatorHolder getNavigatorHolder();

    ResourceManager getResourceManager();

    SettingsHelper getSettingsHelper();

    Representative getRepresentative();

    StorageManager getStorageManager();

    Context getContext();
}
