package ru.supplyphotos;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import io.realm.Realm;

/**
 * @author libgo (05.01.2018)
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        Fresco.initialize(this);

    }

}
