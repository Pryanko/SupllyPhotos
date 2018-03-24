package ru.supplyphotos.presentation.fragments;

import com.arellomobile.mvp.MvpView;

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




    interface CategoryView extends BaseViewFragment {

        void setTouchItemAdapter(ContractsAdapters.ItemCategoryTouch itemCategoryTouch);

        void startShow(List<ItemCategory> list);

        void next();
    }


    interface HeadView extends BaseViewFragment {


    }

    interface ServiceView extends BaseViewFragment{

        void testStart(SelectedItemCategory selectedItemCategory,  List<ItemService> list);

    }


    interface ManualView extends BaseViewFragment {

        void startShow(int size, List<Guide> list);

        void alertShow(String title, String message, String posButton, String negButton);

    }


    interface BaseViewFragment extends MvpView {

        void onError();

        void showLoading(boolean loading);



    }
}
