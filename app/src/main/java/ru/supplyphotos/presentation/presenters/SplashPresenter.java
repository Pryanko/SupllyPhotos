package ru.supplyphotos.presentation.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import io.reactivex.disposables.Disposable;
import ru.supplyphotos.App;
import ru.supplyphotos.presentation.activities.BaseViewActivity;
import ru.supplyphotos.rx.RxNetwork;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.supplyphotos.tools.utils.TokenUtils;

/**
 * @author libgo (05.01.2018)
 */
@InjectViewState
public class SplashPresenter extends MvpPresenter<BaseViewActivity> implements BasePresenter{

    private Disposable disposable;
    private SettingInterface settingInterface;

    public SplashPresenter() {
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        startingApp();

    }

    private void startingApp(){
        
        if(settingInterface.getBooleanRun()){
            playShowLaunch(settingInterface.getBooleanRun());
        }else {
            Log.d("BOOLEAN", String.valueOf(settingInterface.getBooleanRun()));
            disposable = (RxNetwork.getDeviceToken()
                    .doOnNext(deviceToken -> settingInterface.setDeviceToken(deviceToken))
                    .flatMap(TokenUtils::getFlagValidToken)
                    .subscribe(this::playShowLaunch, this::handleError)
            );
        }

    }

    private void playShowLaunch(Boolean run) {
        
        if(run) {
            Log.d("BOOLEAN", String.valueOf(settingInterface.getBooleanRun()));
            getViewState().startShow();
        }
    }

    //implements
    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {
        /*if(!disposable.isDisposed()){
            disposable.dispose();
        }     */

        getViewState().closeScreen();
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }
}
