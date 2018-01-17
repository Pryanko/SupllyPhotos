package ru.supplyphotos.presentation.presenters;

import android.content.Intent;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import ru.supplyphotos.App;
import ru.supplyphotos.presentation.activities.HeadActivity;
import ru.supplyphotos.presentation.activities.SplashActivity;
import ru.supplyphotos.rx.RxNetwork;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @author libgo (05.01.2018)
 */

public class SplashPresenter implements BasePresenter{

    private SplashActivity splashView;
    private SettingsHelper settingsHelper;

    public void setView(SplashActivity splashActivity){
        this.splashView = splashActivity;
        settingsHelper = App.getAppComponent().getSettingsHelper();
    }

    @Override
    public void createView() {
        if(settingsHelper.getBooleanRun()){
            playShow();
        }else {
            Log.d("BOOLEAN", String.valueOf(settingsHelper.getBooleanRun()));
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(RxNetwork.getDeviceToken()
                    .doOnNext(deviceToken -> settingsHelper.setDeviceToken(deviceToken, true))
                    .subscribe(deviceToken -> playShow(), this::handleError)
            );
        }

    }
    @Override
    public void playShow() {
        if(settingsHelper.getBooleanRun()) {
            Log.d("BOOLEAN", String.valueOf(settingsHelper.getBooleanRun()));
            splashView.startShow();
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {
        Intent intent = new Intent(splashView, HeadActivity.class);
        splashView.startActivity(intent);
        splashView.finish();
        settingsHelper = null;
        splashView = null;
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }
}
