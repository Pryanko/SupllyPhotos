package ru.supplyphotos.presentation.activities.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ru.sumplyphotos.logger.Logger;
import ru.sumplyphotos.logger.LoggerFactory;
import ru.supplyphotos.di.Dagger;
import ru.supplyphotos.di.components.AppComponent;

/**
 * Базовый класс для всех {@link Activity} приложения.
 *
 * @author Grigoriy Pryamov.
 */
public class BaseActivity extends AppCompatActivity {

    private final Logger logger = LoggerFactory.getLogger(BaseActivity.class);

    protected final AppComponent appComponent() {
        return Dagger.appComponent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        logger.trace("onCreate: " + this);
//        getWindow().setBackgroundDrawableResource(getBackgroundDrawableResource());
//        // Делаем прозрачный statusBar и непрозрачный navigationBar
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        logger.trace("onNewIntent: " + this);
    }

    @Override
    protected void onStart() {
        logger.trace("onStart: " + this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        logger.trace("onResume: " + this);
        super.onResume();
    }

    @Override
    protected void onPause() {
        logger.trace("onPause: " + this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        logger.trace("onStop: " + this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        logger.trace("onDestroy: " + this);
        super.onDestroy();
    }

    @DrawableRes
    protected int getBackgroundDrawableResource() {
        return android.R.color.white;
    }
}
