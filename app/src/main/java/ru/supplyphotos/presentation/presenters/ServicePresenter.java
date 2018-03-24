package ru.supplyphotos.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import ru.supplyphotos.App;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;

/**
 * @author Libgo on 24.03.2018.
 */
@InjectViewState
public class ServicePresenter extends MvpPresenter<ContractsFragmentView.ServiceView>
        implements BasePresenter {

    private SettingInterface settingInterface;
    private AppRepository appRepository;

    public ServicePresenter() {
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
        this.appRepository = App.getAppComponent().getAppRepository();
    }

    private void getTestInfo(){
        Disposable disposable = appRepository.getListService(settingInterface.getSelectedItemCategory().getId())
                .subscribe(itemServices -> getViewState().testStart(settingInterface
                        .getSelectedItemCategory(), itemServices), throwable -> onError());
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getTestInfo();
    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }
}
