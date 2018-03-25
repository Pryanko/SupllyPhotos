package ru.supplyphotos.presentation.presenters;


import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.manuals.Guide;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.resource.ResourceManager;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.rx.RxNetwork;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;


/**
 * @autor user on 12.01.2018.
 */
@InjectViewState
public class ManualPresenter extends MvpPresenter<ContractsFragmentView.ManualView> implements BasePresenter.Manual {


    private AppRepository appRepository;
    private ResourceManager resourceManager;
    private Router router;

    public ManualPresenter() {
        this.router = App.getAppComponent().getRouter();
        this.appRepository = App.getAppComponent().getAppRepository();
        this.resourceManager = App.getAppComponent().getResourceManager();

    }

    @Override
    public void attachView(ContractsFragmentView.ManualView view) {
        super.attachView(view);
        getViewState().showLoading(true);
        showView();

    }


    public void nextScreen(){
        router.newRootScreen(CATEGORY_SCREEN);

    }

    private void showView() {

        Disposable disposable = appRepository.getGuides()
                .subscribe(this::startViewShow, this::handleError);
    }

    private void startViewShow(Manual manual) {
        getViewState().startShow(manual.getData().size(),manual.getData());
        getViewState().showLoading(false);
    }


    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }
    
    public void missManual(){
        getViewState().alertShow(resourceManager.getString(R.string.alert_show_manual_title),
                resourceManager.getString(R.string.alert_show_manual_message),
                resourceManager.getString(R.string.alert_show_manual_positive_button),
                resourceManager.getString(R.string.alert_show_manual_negative_button));
    }

}
