package ru.supplyphotos.presentation.presenters;



import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import io.reactivex.disposables.CompositeDisposable;
import ru.supplyphotos.App;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.terrakok.cicerone.Router;

import static ru.supplyphotos.constants.Constants.SERVICES_SCREEN;

/**
 * @author Libgo on 20.01.2018.
 */
@InjectViewState
public class CategoryPresenter extends MvpPresenter<ContractsFragmentView.CategoryView>
        implements BasePresenter.Category, ContractsAdapters.ItemCategoryTouch {



    private CompositeDisposable compositeDisposable;
    private AppRepository appRepository;
    private SettingInterface settingInterface;
    private Router router;


    public CategoryPresenter() {
           router = App.getAppComponent().getRouter();
           compositeDisposable = new CompositeDisposable();
           appRepository = App.getAppComponent().getAppRepository();
           settingInterface = App.getAppComponent().getSettingsHelper()
                .getSettingsInterface();
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        compositeDisposable.add(appRepository.getListCategory()
                .subscribe(list -> getViewState().startShow(list), this::handleError));
        getViewState().delegateTouchItemAdapter(this);
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
        if(itemCategory != null){
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



