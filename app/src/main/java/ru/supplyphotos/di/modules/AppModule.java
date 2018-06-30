package ru.supplyphotos.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.App;

/**
 * @author Libgo on 17.01.2018.
 */
@Module
@Singleton
public class AppModule {

    private final static String APP_SETTINGS = "app_settings";

    private final App appContext;
    private final Handler handler;

    public AppModule(App app, Handler handler) {
        this.appContext = app;
        this.handler = handler;
    }

    @Provides
    SharedPreferences sharedPreferences() {
        return appContext.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    @Provides
    Context context() {
        return appContext;
    }

    @Provides
    App app() {
        return appContext;
    }

    @Provides
    Handler handler() {
        return handler;
    }
}
