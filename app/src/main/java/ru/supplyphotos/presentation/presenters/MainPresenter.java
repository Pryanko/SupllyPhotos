package ru.supplyphotos.presentation.presenters;



import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.supplyphotos.App;
import ru.supplyphotos.presentation.activities.MainView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.BASKET_SCREEN;
import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;
import static ru.supplyphotos.constants.Constants.MANUAL_SCREEN;
import static ru.supplyphotos.constants.Constants.REGISTER_SCREEN;
import static ru.supplyphotos.constants.Constants.SERVICES_SCREEN;

/**
 * @author Libgo on 24.01.2018.
 */
@InjectViewState
public class MainPresenter  extends MvpPresenter<MainView> implements BasePresenter.HeadView,
        BasePresenter.ControlForToolbar {

    private Representative representative;
    private SettingInterface settingInterface;
    private Router router;

    public MainPresenter() {
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
        this.router = App.getAppComponent().getRouter();
        this.representative = App.getAppComponent().getRepresentative();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        representative.setRepresentative(this);
        getViewState().startShow(settingInterface.getBooleanRun());
        start(settingInterface.getBooleanRun());
        if(!settingInterface.getBooleanRun()){
            settingInterface.setFirstRun(true);
        }

    }

    private void start(boolean firstRun){
        if (firstRun) {
            router.newRootScreen(CATEGORY_SCREEN);
           // router.newRootScreen(REGISTER_SCREEN);
        } else {
            router.replaceScreen(MANUAL_SCREEN);
        }

    }


   

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void setTypeScreen(String typeScreen) {
        switch (typeScreen){
            case CATEGORY_SCREEN:
            getViewState().controlToolbar(true, true);
            break;
            case SERVICES_SCREEN:
                getViewState().controlToolbar(false, true);
                break;
            case BASKET_SCREEN:
                getViewState().controlToolbar(false, false);
                break;
        }

    }

    @Override
    public void openBasket() {
        router.navigateTo(BASKET_SCREEN);
    }
}
