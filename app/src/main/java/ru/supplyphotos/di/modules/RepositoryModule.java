package ru.supplyphotos.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.supplyphotos.data.repository.AppRepository;

/**
 * @author Libgo on 15.03.2018.
 */
@Module
@Singleton
public class RepositoryModule {


    @Provides
    @Singleton
    AppRepository appRepository(){
        return new AppRepository();
    }

}
