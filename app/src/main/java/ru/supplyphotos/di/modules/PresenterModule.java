package ru.supplyphotos.di.modules;

import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.presentation.presenters.ManualPresenter;
import ru.supplyphotos.presentation.presenters.SplashPresenter;

/**
 * @autor user on 12.01.2018.
 */
@Module
public class PresenterModule {

    @Provides
    SplashPresenter splashPresenter(){
        return new SplashPresenter();
    }

    @Provides
    ManualPresenter manualPresenter() {
        return new ManualPresenter();
    }
}
