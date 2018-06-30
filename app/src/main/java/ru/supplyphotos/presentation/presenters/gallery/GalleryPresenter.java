package ru.supplyphotos.presentation.presenters.gallery;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import ru.supplyphotos.data.db.RealmDataBase;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.fragments.head.gallery_fragments.ContractsGalleryFragmentView;
import ru.supplyphotos.presentation.presenters.BasePresenter;


/**
 * @author Libgo on 28.03.2018.
 */
@InjectViewState
public class GalleryPresenter extends MvpPresenter<ContractsGalleryFragmentView.PhoneGalleryView>
                        implements BasePresenter.Gallery, ContractsAdapters.GalleryTouchManager{
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final AppRepository appRepository;
    private RealmDataBase.UpdateTable touchManager;

    @Inject
    public GalleryPresenter(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().checkPermission();
        Log.d("onFirstViewAttach", "GalleryPresenter");
    }

    @Override
    public void attachView(ContractsGalleryFragmentView.PhoneGalleryView view) {
        super.attachView(view);
        getViewState().setTouchManager(this);
        this.touchManager = appRepository.getTouchManager();
    }

    @Override
    public void destroyView(ContractsGalleryFragmentView.PhoneGalleryView view) {
        super.destroyView(view);
        compositeDisposable.clear();
    }

    //Implemetns
    @Override
    public void loadImage() {
        getViewState().showLoading(true);

        compositeDisposable.add(appRepository.getImageFlowable()
                .doOnNext(itemStorageImages -> getViewState().updateAdapterList(itemStorageImages))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(itemStorageImages -> getViewState().showLoading(false))
                .subscribe(itemStorageImages -> getViewState().showGallery()));


    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }




    @Override
    public void updateCountPrintImage(Integer item_id, Integer count) {
           touchManager.updateCountPrintImage(item_id, count);
    }

    @Override
    public void switchSelectedItemImage(Integer item_id, Boolean isSelectedItem) {
            touchManager.switchSelectedItemImage(item_id, isSelectedItem);
    }
}
