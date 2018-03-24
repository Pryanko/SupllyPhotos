package ru.supplyphotos.presentation.presenters;



import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.supplyphotos.App;
import ru.supplyphotos.presentation.activities.MainView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @author Libgo on 24.01.2018.
 */
@InjectViewState
public class MainPresenter  extends MvpPresenter<MainView> implements BasePresenter {

    private SettingInterface settingInterface;

    public MainPresenter() {
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().startShow(/*settingsHelper.getBooleanRun()*/false);
        if(!settingInterface.getBooleanRun()){
            settingInterface.setFirstRun(true);
        }

    }


    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }
}
