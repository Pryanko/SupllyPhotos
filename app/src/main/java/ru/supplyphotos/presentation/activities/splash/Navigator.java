package ru.supplyphotos.presentation.activities.splash;

import javax.inject.Inject;

import ru.supplyphotos.di.ScreenScope;
import ru.supplyphotos.presentation.activities.ActivityNavigator;

/**
 * @author Grigoriy Pryamov.
 */
@ScreenScope
public class Navigator {

    private SplashActivity activity;
    private final ActivityNavigator activityNavigator;

    @Inject
    Navigator(ActivityNavigator activityNavigator) {
        this.activityNavigator = activityNavigator;
    }

    void onResume(SplashActivity splashActivity) {
        this.activity = splashActivity;
    }

    void onPause() {
        this.activity = null;
    }

    void navigateToHeadActivity() {
        activityNavigator.navigateToHeadActivity(activity);
    }
}

