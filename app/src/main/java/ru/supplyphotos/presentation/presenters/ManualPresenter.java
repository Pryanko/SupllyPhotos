package ru.supplyphotos.presentation.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;


/**
 * @autor user on 12.01.2018.
 */
@InjectViewState
public class ManualPresenter extends MvpPresenter<ContractsFragmentView.ManualView> implements BasePresenter.Manual {


    private final AppRepository appRepository;
    private final ResourceManager resourceManager;
    private final Router router;
    private Disposable disposable;

    @Inject
    public ManualPresenter(AppRepository appRepository,
                           ResourceManager resourceManager,
                           Router router) {
        this.appRepository = appRepository;
        this.resourceManager = resourceManager;
        this.router = router;
    }

    @Override
    public void attachView(ContractsFragmentView.ManualView view) {
        super.attachView(view);
        getViewState().showLoading(true);
        showView();

    }

    @Override
    public void destroyView(ContractsFragmentView.ManualView view) {
        super.destroyView(view);
        disposable.dispose();
    }

    public void nextScreen() {
        router.newRootScreen(CATEGORY_SCREEN);

    }

    private void showView() {

        disposable = appRepository.getGuides()
                .subscribe(this::startViewShow, this::handleError);
    }

    private void startViewShow(Manual manual) {
        getViewState().startShow(manual.getData().size(), manual.getData());
        getViewState().showLoading(false);
    }


    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }

    public void missManual() {
        getViewState().alertShow(resourceManager.getString(R.string.alert_show_manual_title),
                resourceManager.getString(R.string.alert_show_manual_message),
                resourceManager.getString(R.string.alert_show_manual_positive_button),
                resourceManager.getString(R.string.alert_show_manual_negative_button));
    }

}
