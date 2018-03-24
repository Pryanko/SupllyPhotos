package ru.supplyphotos;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import ru.supplyphotos.di.components.AppComponent;
import ru.supplyphotos.di.components.DaggerAppComponent;
import ru.supplyphotos.di.modules.ContextModule;
import ru.supplyphotos.di.modules.SettingModule;


/**
 * @author libgo (05.01.2018)
 */

public class App extends Application{
    
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        Fresco.initialize(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


}
