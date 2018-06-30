package ru.supplyphotos;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.sumplyphotos.logger.Logger;
import ru.sumplyphotos.logger.LoggerFactory;
import ru.supplyphotos.di.Dagger;
import ru.supplyphotos.di.components.DaggerAppComponent;
import ru.supplyphotos.di.modules.AppModule;

/**
 * Класс для иницализации приложения.
 *
 * @author Grigoriy Pryamov.
 */
public class AppInitProvider extends ContentProvider {

    private static final Logger logger = LoggerFactory.getLogger(AppInitProvider.class);

    @Override
    public boolean onCreate() {
        logger.trace("onCreate");
        initDi(getContext());
        return false;
    }

    private void initDi(Context appContext) {
        App app = (App) appContext;
        Dagger.setAppComponent(DaggerAppComponent.builder()
                .appModule(new AppModule(app, new Handler()))
                .build());
        Dagger.appComponent().inject(this);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
