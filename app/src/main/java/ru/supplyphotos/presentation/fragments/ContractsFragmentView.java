package ru.supplyphotos.presentation.fragments;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.manuals.Guide;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.settings.SelectedItemCategory;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;

/**
 * @author Libgo on 20.03.2018.
 */

public interface ContractsFragmentView {

    interface BasketView extends BaseViewFragment{

    }


    interface RegisterView extends BaseViewFragment{

    }

    interface DescriptionView extends BaseViewFragment{

        //implements
        void startDescription(ItemService itemService);
    }


    interface UploadView extends BaseViewFragment{

        void setUploadMaxProgress(Integer endUpload);

        void setUploadStatus(Integer intStatus);

        void setTextStatus(Integer intStatus, Integer endUpload);

        void setCompleteText(String s);
    }



    interface CategoryView extends BaseViewFragment {

        void delegateTouchItemAdapter(ContractsAdapters.ItemCategoryTouch itemCategoryTouch);

        void startShow(List<ItemCategory> list);
        
    }


    interface HeadView extends BaseViewFragment {


    }

    interface ServiceView extends BaseViewFragment{

        void delegateTouchItemAdapter(ContractsAdapters.ItemServiceTouch itemServiceTouch);

        void testStart(SelectedItemCategory selectedItemCategory,  List<ItemService> list);

    }


    interface ManualView extends BaseViewFragment {

        void startShow(int size, List<Guide> list);

        void alertShow(String title, String message, String posButton, String negButton);

    }

    @StateStrategyType(AddToEndStrategy.class)
    interface BaseViewFragment extends MvpView {


        void onError();

        void showLoading(boolean loading);



    }
}
