package ru.supplyphotos.presentation.presenters;

import android.content.Intent;
import ru.supplyphotos.presentation.activities.HeadActivity;
import ru.supplyphotos.presentation.activities.SplashActivity;

/**
 * @author libgo (05.01.2018)
 */

public class SplashPresenter implements BasePresenter{

    private SplashActivity splashView;

    public void setView(SplashActivity splashActivity){
        this.splashView = splashActivity;
    }

    @Override
    public void createView() {
        splashView.startShow();
    }

    @Override
    public void playShow() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {
        Intent intent = new Intent(splashView, HeadActivity.class);
        splashView.startActivity(intent);
        splashView.finish();
        splashView = null;
    }
}
