package ru.supplyphotos.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.di.modules.ContextModule;
import ru.supplyphotos.di.modules.NavigationModule;
import ru.supplyphotos.di.modules.RepositoryModule;
import ru.supplyphotos.di.modules.SettingModule;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * @autor user on 12.01.2018.
 */
@Singleton
@Component(modules = {NavigationModule.class, SettingModule.class, ContextModule.class, RepositoryModule.class})
public interface AppComponent {

    AppRepository getAppRepository();

    Router getRouter();

    NavigatorHolder getNavigatorHolder();

    ResourceManager getResourceManager();

    SettingsHelper getSettingsHelper();

    Context getContext();
}
