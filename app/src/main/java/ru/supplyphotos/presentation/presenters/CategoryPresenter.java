package ru.supplyphotos.presentation.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.CATEGORY_SCREEN;
import static ru.supplyphotos.constants.Constants.SERVICES_SCREEN;

/**
 * @author Libgo on 20.01.2018.
 */
@InjectViewState
public class CategoryPresenter extends MvpPresenter<ContractsFragmentView.CategoryView>
        implements BasePresenter.Category, ContractsAdapters.ItemCategoryTouch {


    private final Representative representative;
    private final CompositeDisposable compositeDisposable;
    private final AppRepository appRepository;
    private final SettingInterface settingInterface;
    private final Router router;

    @Inject
    public CategoryPresenter(Representative representative,
                             CompositeDisposable compositeDisposable,
                             SettingsHelper settingsHelper,
                             AppRepository appRepository,
                             Router router) {
        this.representative = representative;
        this.compositeDisposable = compositeDisposable;
        this.settingInterface = settingsHelper.getSettingsInterface();
        this.appRepository = appRepository;
        this.router = router;
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        compositeDisposable.add(appRepository.getListCategory()
                .subscribe(list -> getViewState().startShow(list), this::handleError));
        getViewState().delegateTouchItemAdapter(this);
    }

    @Override
    public void attachView(ContractsFragmentView.CategoryView view) {
        super.attachView(view);
        representative.switchTypeScreen(CATEGORY_SCREEN);
    }

    @Override
    public void destroyView(ContractsFragmentView.CategoryView view) {
        super.destroyView(view);
        compositeDisposable.clear();
    }

    private void handleError(Throwable throwable) {
    }


    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {
        compositeDisposable.dispose();
    }


    @Override
    public void touchItemCategory(ItemCategory itemCategory) {
        if (itemCategory != null) {
            settingInterface.saveSelectedItemCategory(itemCategory);
            nextScreen();
            Log.d("ПРОВЕРКА", itemCategory.getName());
        }

    }

    @Override
    public void nextScreen() {
        router.navigateTo(SERVICES_SCREEN);
    }
}



