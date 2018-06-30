package ru.supplyphotos.di;

import ru.supplyphotos.di.components.AppComponent;

/**
 * @author Grigoriy Pryamov.
 */
public class Dagger {

    private static AppComponent appComponent;

    public static AppComponent appComponent() {
        return appComponent;
    }

    public static void setAppComponent(AppComponent appComponent) {
        Dagger.appComponent = appComponent;
    }
}
