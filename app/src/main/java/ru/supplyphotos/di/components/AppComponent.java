package ru.supplyphotos.di.components;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;
import ru.supplyphotos.di.modules.PresenterModule;
import ru.supplyphotos.presentation.presenters.ManualPresenter;
import ru.supplyphotos.presentation.presenters.SplashPresenter;

/**
 * @autor user on 12.01.2018.
 */
@Singleton
@Component(modules = {PresenterModule.class})
public interface AppComponent {

    SplashPresenter getSplashPresenter();

    ManualPresenter getManualPresenter();
}
