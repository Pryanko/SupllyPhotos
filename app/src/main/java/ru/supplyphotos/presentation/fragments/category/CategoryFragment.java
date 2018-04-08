package ru.supplyphotos.presentation.fragments.category;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;


import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.presentation.activities.MainActivity;
import ru.supplyphotos.presentation.adapters.CategoryAdapter;
import ru.supplyphotos.presentation.adapters.ContractsAdapters;
import ru.supplyphotos.presentation.adapters.decoration.SpaceDecoration;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.fragments.head.HeadFragment;
import ru.supplyphotos.presentation.fragments.services.ServiceFragment;
import ru.supplyphotos.presentation.presenters.CategoryPresenter;
import ru.terrakok.cicerone.Router;

/**
 * @author Libgo on 20.01.2018.
 */

public class CategoryFragment extends MvpAppCompatFragment implements
        ContractsFragmentView.CategoryView{
    
    //Bind
    @BindView(R.id.category_recycler)
    RecyclerView categoryRecycler;

    private CategoryAdapter categoryAdapter;

    

    @InjectPresenter
    CategoryPresenter categoryPresenter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        categoryAdapter = new CategoryAdapter();



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        categoryRecycler.addItemDecoration(new SpaceDecoration(12));
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        menu.findItem(R.id.shop_button);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void startShow(List<ItemCategory> list){
        categoryAdapter.addCategoryList(list);
        categoryRecycler.setAdapter(categoryAdapter);

    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryPresenter.destroyView();
    }

    public void delegateTouchItemAdapter(ContractsAdapters.ItemCategoryTouch itemCategoryTouch){
        categoryAdapter.setItemCategoryTouch(itemCategoryTouch);
    }

    @Override
    public void onError() {

    }



    @Override
    public void showLoading(boolean loading) {
         //not progressBar
    }
}
