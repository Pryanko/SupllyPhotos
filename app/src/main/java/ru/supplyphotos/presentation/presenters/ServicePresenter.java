package ru.supplyphotos.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;
import ru.supplyphotos.App;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.DESCRIPTION_SCREEN;


/**
 * @author Libgo on 24.03.2018.
 */
@InjectViewState
public class ServicePresenter extends MvpPresenter<ContractsFragmentView.ServiceView>
        implements BasePresenter.Contracts, ContractsAdapters.ItemServiceTouch {

    private SettingInterface settingInterface;
    private AppRepository appRepository;
    private Router router;
    private Disposable disposable;

    public ServicePresenter() {
        this.settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
        this.appRepository = App.getAppComponent().getAppRepository();
        this.router = App.getAppComponent().getRouter();
       // getViewState().delegateTouchItemAdapter(this);
    }

    @Override
    public void attachView(ContractsFragmentView.ServiceView view) {
        super.attachView(view);
        getViewState().delegateTouchItemAdapter(this);
    }

    private void getTestInfo(){
        disposable = appRepository.getListService(settingInterface.getSelectedItemCategory().getId())
                .subscribe(itemServices -> getViewState().testStart(settingInterface
                        .getSelectedItemCategory(), itemServices), this::onError);
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getTestInfo();
    }

    @Override
    public void destroyView(ContractsFragmentView.ServiceView view) {
        super.destroyView(view);
        disposable.dispose();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }

    @Override
    public void touchItemService(Integer service_id) {
        settingInterface.saveSelectedServiceId(service_id);
        router.navigateTo(DESCRIPTION_SCREEN);
    }
}
