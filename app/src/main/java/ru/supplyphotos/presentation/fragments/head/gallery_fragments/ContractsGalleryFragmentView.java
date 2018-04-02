package ru.supplyphotos.presentation.fragments.head.gallery_fragments;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;

/**
 * @author Libgo on 28.03.2018.
 */
public interface ContractsGalleryFragmentView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    interface PhoneGalleryView extends ContractsFragmentView.BaseViewFragment{
        //@StateStrategyType(SkipStrategy.class)
        void updateAdapterList(List<ItemStorageImage> itemStorageImages);
        
        @StateStrategyType(SkipStrategy.class)
        void checkPermission();

        void setTouchManager(ContractsAdapters.GalleryTouchManager galleryTouchManager);

        void showGallery();

    }


}
