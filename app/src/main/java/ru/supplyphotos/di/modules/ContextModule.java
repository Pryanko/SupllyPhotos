package ru.supplyphotos.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Libgo on 17.01.2018.
 */
@Module
@Singleton
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context context(){
        return context;
    }
}
