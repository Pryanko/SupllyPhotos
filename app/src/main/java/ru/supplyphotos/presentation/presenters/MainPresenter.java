package ru.supplyphotos.presentation.presenters;



import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.activities.MainView;
import ru.supplyphotos.presentation.fragments.category.CategoryFragment;
import ru.supplyphotos.presentation.fragments.manuals.ManualFragment;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;
import static ru.supplyphotos.constants.Constants.HEAD_SCREEN;
import static ru.supplyphotos.constants.Constants.MANUAL_SCREEN;

/**
 * @author Libgo on 24.01.2018.
 */
@InjectViewState
public class MainPresenter  extends MvpPresenter<MainView> implements BasePresenter.Contracts {

    private SettingInterface settingInterface;
    private Router router;

    public MainPresenter() {
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
        this.router = App.getAppComponent().getRouter();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().startShow(settingInterface.getBooleanRun());
        start(settingInterface.getBooleanRun());
        if(!settingInterface.getBooleanRun()){
            settingInterface.setFirstRun(true);
        }

    }

    private void start(boolean firstRun){
        if (firstRun) {
            router.newRootScreen(CATEGORY_SCREEN);
           //router.newRootScreen(HEAD_SCREEN);
        } else {
            router.replaceScreen(MANUAL_SCREEN);
        }

    }


   

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }
}
