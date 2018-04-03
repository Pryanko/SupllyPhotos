package ru.supplyphotos.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.repository.UploadRepository;

/**
 * @author Libgo on 15.03.2018.
 */
@Module
@Reusable
public class RepositoryModule {


    @Provides
    @Reusable
    AppRepository appRepository(){
        return new AppRepository();
    }


    @Provides
    @Reusable
    UploadRepository uploadRepository() {
        return new UploadRepository();
    }

}
