package ru.supplyphotos.di.modules;

import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.supplyphotos.R;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.presentation.activities.MainActivity;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

/**
 * @author Libgo on 25.03.2018.
 */
@Module
@Singleton
public class NavigationModule {

    private Cicerone<Router> cicerone;

    public NavigationModule() {
        cicerone = Cicerone.create();
    }

    @Provides
    @Singleton
    NavigatorHolder navigatorHolder(){
        return cicerone.getNavigatorHolder();
    }

    @Provides
    @Singleton
    Router router(){
        return cicerone.getRouter();
    }

}
