package ru.supplyphotos.presentation.presenters.gallery;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.supplyphotos.App;
import ru.supplyphotos.data.repository.AppRepository;
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

    public GalleryPresenter() {
        appRepository = App.getAppComponent().getAppRepository();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().checkPermission();
    }

    //Implemetns
    @Override
    public void loadImage() {
        getViewState().showLoading(true);
        compositeDisposable.add(appRepository.getListItemImageStorge()
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
