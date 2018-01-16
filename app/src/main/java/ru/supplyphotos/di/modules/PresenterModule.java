package ru.supplyphotos.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.presentation.presenters.ManualPresenter;
import ru.supplyphotos.presentation.presenters.SplashPresenter;

/**
 * @autor user on 12.01.2018.
 */
@Module
@Singleton
public class PresenterModule {

    @Provides
    @Singleton
    SplashPresenter splashPresenter(){
        return new SplashPresenter();
    }

    @Provides
    @Singleton
    ManualPresenter manualPresenter() {
        return new ManualPresenter();
    }
}
