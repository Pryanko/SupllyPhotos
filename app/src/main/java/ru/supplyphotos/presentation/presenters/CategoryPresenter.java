package ru.supplyphotos.presentation.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.presentation.fragments.category.CategoryFragment;
import ru.supplyphotos.rx.RxNetwork;

/**
 * @author Libgo on 20.01.2018.
 */

public class CategoryPresenter implements BasePresenter {

    private  CategoryFragment categoryFragment;
    private List<ItemCategory> list = new ArrayList<>();

    public void setView(CategoryFragment fragment){
        this.categoryFragment = fragment;
    }

    private void setList(List<ItemCategory> itemCategoryList){
        this.list = itemCategoryList;
        Log.d("LIST", list.toString());
    }

    @Override
    public void createView() {

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(RxNetwork.getListCategory()
                .doOnNext(this::setList)
                .subscribe(deviceToken -> playShow(), this::handleError));


    }

    private void handleError(Throwable throwable) {
    }

    @Override
    public void playShow() {
        categoryFragment.startShow(list);
    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {
         categoryFragment = null;
    }
}
