package ru.supplyphotos.presentation.activities.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.di.ActivityScope;

/**
 * @author Grigoriy Pryamov.
 */
@ActivityScope
@Module
public class ActivityModule {

    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Activity activity() {
        return activity;
    }

    @Provides
    public AppCompatActivity appCompatActivity() {
        return activity;
    }

    @Provides
    public BaseActivity baseActivity() {
        return activity;
    }
}
