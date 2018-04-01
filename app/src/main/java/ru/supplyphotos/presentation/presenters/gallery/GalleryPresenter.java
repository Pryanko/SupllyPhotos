package ru.supplyphotos.presentation.presenters.gallery;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import ru.supplyphotos.App;
import ru.supplyphotos.data.db.DataBaseSource;
import ru.supplyphotos.data.repository.AppRepository;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.data.storage.StorageManager;
import ru.supplyphotos.presentation.fragments.head.gallery_fragments.ContractsGalleryFragmentView;
import ru.supplyphotos.presentation.presenters.BasePresenter;

/**
 * @author Libgo on 28.03.2018.
 */
@InjectViewState
public class GalleryPresenter extends MvpPresenter<ContractsGalleryFragmentView.PhoneGalleryView>
                        implements BasePresenter.Gallery{
    private CompositeDisposable compositeDisposable;
    private AppRepository appRepository;
    private StorageManager storageManager;
    private DataBaseSource dataBaseSource;

    public GalleryPresenter() {
        appRepository = App.getAppComponent().getAppRepository();
        compositeDisposable = new CompositeDisposable();
        storageManager = App.getAppComponent().getStorageManager();
        dataBaseSource = App.getAppComponent().getDataBaseSource();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().checkPermission();
    }

    private Single<List<ItemStorageImage>> getSubscribe(){

        return Flowable.concat(Flowable.just(dataBaseSource.getItemDBImage()), storageManager.getListItemsStorageImage())
                .first(Collections.emptyList());
    }

    //Implemetns
    @Override
    public void loadImage() {
        getViewState().showLoading(true);

        compositeDisposable.add(Flowable.just(dataBaseSource.getItemDBImage())
                .subscribeOn(Schedulers.io())
                .doOnNext(itemStorageImages -> getViewState().updateAdapterList(itemStorageImages))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(itemStorageImages -> getViewState().showLoading(false))
                .subscribe(itemStorageImages -> getViewState().showGallery()));


    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }
}
