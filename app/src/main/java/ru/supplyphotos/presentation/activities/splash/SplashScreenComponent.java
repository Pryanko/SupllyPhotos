package ru.supplyphotos.presentation.activities.splash;

import dagger.Subcomponent;
import ru.supplyphotos.di.ScreenScope;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
@Subcomponent
public interface SplashScreenComponent {

    SplashComponent splashComponent();
}
