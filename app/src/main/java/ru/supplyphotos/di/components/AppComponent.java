package ru.supplyphotos.di.components;

import android.content.Context;

import com.arellomobile.mvp.presenter.ProvidePresenterTag;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.di.modules.ContextModule;
import ru.supplyphotos.di.modules.PresenterModule;
import ru.supplyphotos.di.modules.RepositoryModule;
import ru.supplyphotos.di.modules.SettingModule;
import ru.supplyphotos.presentation.presenters.CategoryPresenter;
import ru.supplyphotos.presentation.presenters.MainPresenter;
import ru.supplyphotos.presentation.presenters.ManualPresenter;
import ru.supplyphotos.presentation.presenters.SplashPresenter;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @autor user on 12.01.2018.
 */
@Singleton
@Component(modules = {PresenterModule.class, SettingModule.class, ContextModule.class, RepositoryModule.class})
public interface AppComponent {

    AppRepository getAppRepository();

    CategoryPresenter getCategoryPresenter();

    ResourceManager getResourceManager();

    SettingsHelper getSettingsHelper();

    Context getContext();
}
