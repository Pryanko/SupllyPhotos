package ru.supplyphotos.di.modules;


import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import ru.supplyphotos.data.db.DataBaseSource;

/**
 * @author libgo (01.04.2018)
 */

@Module
@Reusable
public class DataBaseModule {


    @Provides
    @Reusable
    DataBaseSource dataBaseSource(){
        return new DataBaseSource();
    }

}
