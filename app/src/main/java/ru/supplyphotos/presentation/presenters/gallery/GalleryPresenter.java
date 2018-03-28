package ru.supplyphotos.presentation.presenters.gallery;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.supplyphotos.presentation.fragments.head.gallery_fragments.ContractsGalleryFragmentView;
import ru.supplyphotos.presentation.presenters.BasePresenter;

/**
 * @author Libgo on 28.03.2018.
 */
@InjectViewState
public class GalleryPresenter extends MvpPresenter<ContractsGalleryFragmentView.PhoneGalleryView>
                        implements BasePresenter.Gallery{






    //Implemetns
    @Override
    public void loadImage() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }
}
