package ru.supplyphotos;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.supplyphotos.di.components.AppComponent;
import ru.supplyphotos.di.components.DaggerAppComponent;
import ru.supplyphotos.di.modules.ContextModule;

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

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("table_item_image.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(configuration);

        Fresco.initialize(this);

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
