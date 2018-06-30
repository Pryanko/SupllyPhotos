package ru.supplyphotos.di.modules;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.supplyphotos.data.repository.AppRepository;

/**
 * @author Libgo on 15.03.2018.
 */
@Module
@Reusable
public interface RepositoryModule {

    @Reusable
    AppRepository appRepository();


//    @Provides
//    @Reusable
//    UploadRepository uploadRepository() {
//        return new UploadRepository(dataBaseSource);
//    }

}
