package ru.supplyphotos.presentation.activities.splash;

import dagger.Subcomponent;
import ru.supplyphotos.di.ActivityScope;
import ru.supplyphotos.presentation.activities.base.ActivityModule;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface SplashComponent {

    void inject(SplashActivity splashActivity);

    SplashPresenter splashPresenter();
}
