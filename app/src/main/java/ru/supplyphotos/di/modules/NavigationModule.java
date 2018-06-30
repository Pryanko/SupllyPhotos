package ru.supplyphotos.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

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
    NavigatorHolder navigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    @Provides
    @Singleton
    Router router() {
        return cicerone.getRouter();
    }

}
