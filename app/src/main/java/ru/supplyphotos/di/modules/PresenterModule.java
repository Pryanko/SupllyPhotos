package ru.supplyphotos.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.presentation.presenters.CategoryPresenter;
import ru.supplyphotos.presentation.presenters.MainPresenter;
import ru.supplyphotos.presentation.presenters.ManualPresenter;
import ru.supplyphotos.presentation.presenters.SplashPresenter;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @autor user on 12.01.2018.
 */
@Module
public class PresenterModule {
    
    @Provides
    CategoryPresenter categoryPresenter(){
        return new CategoryPresenter();
    }


}
