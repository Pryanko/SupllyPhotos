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

/**
 * @author Libgo on 20.01.2018.
 */
@InjectViewState
public class CategoryPresenter extends MvpPresenter<ContractsFragmentView.CategoryView>
        implements BasePresenter, ContractsAdapters.ItemCategoryTouch {



    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private AppRepository appRepository = App.getAppComponent().getAppRepository();
    private SettingInterface settingInterface = App.getAppComponent().getSettingsHelper()
            .getSettingsInterface();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        compositeDisposable.add(appRepository.getListCategory()
                .subscribe(list -> getViewState().startShow(list), this::handleError));

        getViewState().setTouchItemAdapter(this);
    }




    private void handleError(Throwable throwable) {
    }



    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {
        compositeDisposable.dispose();
    }


    @Override
    public void touchItemCategory(ItemCategory itemCategory) {
        if(itemCategory != null){
        settingInterface.saveSelectedItemCategory(itemCategory);
        getViewState().next();
            Log.d("ПРОВЕРКА", itemCategory.getName());
        }

    }
}



