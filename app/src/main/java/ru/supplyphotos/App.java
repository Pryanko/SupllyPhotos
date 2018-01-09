package ru.supplyphotos;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author libgo (05.01.2018)
 */

public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
