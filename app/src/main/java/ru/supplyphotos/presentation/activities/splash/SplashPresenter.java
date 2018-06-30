package ru.supplyphotos.presentation.activities.splash;


import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ru.sumplyphotos.logger.Logger;
import ru.sumplyphotos.logger.LoggerFactory;
import ru.supplyphotos.presentation.mvp.presenter.BaseMvpViewStatePresenter;
import ru.supplyphotos.rx.RxNetwork;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.supplyphotos.tools.utils.TokenUtils;

/**
 * @author libgo (05.01.2018)
 */
public class SplashPresenter extends BaseMvpViewStatePresenter<SplashView, SplashViewState> {

    private static final Logger logger = LoggerFactory.getLogger(SplashPresenter.class);

    private Disposable disposable = Disposables.disposed();
    private final SettingInterface settingInterface;
    private final Navigator navigator;

    @Inject
    SplashPresenter(SplashViewState viewState,
                    SettingsHelper settingsHelper,
                    Navigator navigator) {
        super(viewState);
        this.settingInterface = settingsHelper.getSettingsInterface();
        this.navigator = navigator;
    }

    @Override
    protected void onInitialize() {
        logger.trace("onInitialize");
        if (settingInterface.getBooleanRun()) {
            view.setButtonVisible(true);
        } else {
            logger.trace("initialize the first run: " + settingInterface.getBooleanRun());
            disposable = (RxNetwork.getDeviceToken()
                    .doOnNext(settingInterface::setDeviceToken)
                    .flatMap(TokenUtils::getFlagValidToken)
                    .subscribe(view::setButtonVisible, logger::error));
        }
    }

    @Override
    public void destroy() {
        disposable.dispose();
        super.destroy();
    }

    public void onNextBtnClicked() {
        logger.trace("onNextBtnClicked");
        navigator.navigateToHeadActivity();
    }
}
