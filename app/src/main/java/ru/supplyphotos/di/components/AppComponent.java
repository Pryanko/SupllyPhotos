package ru.supplyphotos.di.components;

import android.content.Context;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;
import ru.supplyphotos.di.modules.ContextModule;
import ru.supplyphotos.di.modules.PresenterModule;
import ru.supplyphotos.di.modules.SettingModule;
import ru.supplyphotos.presentation.presenters.ManualPresenter;
import ru.supplyphotos.presentation.presenters.SplashPresenter;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @autor user on 12.01.2018.
 */
@Singleton
@Component(modules = {PresenterModule.class, SettingModule.class, ContextModule.class})
public interface AppComponent {
    
    SplashPresenter getSplashPresenter();

    ManualPresenter getManualPresenter();

    SettingsHelper getSettingsHelper();

    Context getContext();
}
