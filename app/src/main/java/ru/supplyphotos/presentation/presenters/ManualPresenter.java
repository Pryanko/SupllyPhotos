package ru.supplyphotos.presentation.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.supplyphotos.data.answers.Manual;
import ru.supplyphotos.network.ApiService;
import ru.supplyphotos.presentation.fragments.manuals.ManualFragment;

/**
 * @autor user on 12.01.2018.
 */

public class ManualPresenter implements BasePresenter {

    private ManualFragment manualView;
    private List<Manual> manuals = new ArrayList<>();

    public void setView(ManualFragment manualFragment){
        this.manualView = manualFragment;
    }

    @Override
    public void createView() {
        ApiService apiService = ApiService.retrofit.create(ApiService.class);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(apiService.getManuals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::addManualList, this::handleError)
        );
    }
    
    @Override
    public void playShow() {
        manualView.startShow();

    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }

    private void addManualList(List<Manual> list){
        Log.d("ZZZZZ", list.toString());
        
        this.manuals = list;
        Log.d("ZZZZZ", manuals.get(0).getHeader());
        playShow();
    }

    public Integer getSizeListManual(){
        return manuals.size();
    }

    public Manual getManual (int position){
        return manuals.get(position);
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }
}
