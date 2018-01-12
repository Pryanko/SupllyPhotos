package ru.supplyphotos;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import ru.supplyphotos.di.components.AppComponent;
import ru.supplyphotos.di.components.DaggerAppComponent;


/**
 * @author libgo (05.01.2018)
 */

public class App extends Application{
    
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .build();

        Fresco.initialize(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
