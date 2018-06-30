package ru.supplyphotos.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.DESCRIPTION_SCREEN;
import static ru.supplyphotos.constants.Constants.SERVICES_SCREEN;


/**
 * @author Libgo on 24.03.2018.
 */
@InjectViewState
public class ServicePresenter extends MvpPresenter<ContractsFragmentView.ServiceView>
        implements BasePresenter.Contracts, ContractsAdapters.ItemServiceTouch {

    private final SettingInterface settingInterface;
    private final AppRepository appRepository;
    private final Router router;
    private Disposable disposable = Disposables.disposed();
    private final Representative representative;

    public ServicePresenter(AppRepository appRepository,
                            Router router,
                            SettingsHelper settingsHelper,
                            Representative representative) {
        this.appRepository = appRepository;
        this.settingInterface = settingsHelper.getSettingsInterface();
        this.router = router;
        this.representative = representative;
    }

    @Override
    public void attachView(ContractsFragmentView.ServiceView view) {
        super.attachView(view);
        getViewState().delegateTouchItemAdapter(this);
        representative.switchTypeScreen(SERVICES_SCREEN);
    }

    private void getTestInfo() {
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
