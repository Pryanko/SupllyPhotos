package ru.supplyphotos.presentation.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import ru.supplyphotos.App;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.terrakok.cicerone.Router;


import static ru.supplyphotos.constants.Constants.HEAD_SCREEN;

/**
 * @author Libgo on 08.04.2018.
 */

@InjectViewState
public class DescriptionPresenter extends MvpPresenter<ContractsFragmentView.DescriptionView>
        implements BasePresenter.Description {

    private Router router;
    private AppRepository appRepository;
    private SettingInterface settingInterface;
    Disposable disposable;

    public DescriptionPresenter() {
        router = App.getAppComponent().getRouter();
        appRepository = App.getAppComponent().getAppRepository();
        settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        disposable = appRepository.getItemServiceForDescription(settingInterface.getSelectedServiceId())
                .subscribe(itemService -> getViewState().startDescription(itemService),
                        this::onError);
    }

    @Override
    public void nextScreen() {
       router.navigateTo(HEAD_SCREEN);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }


}
