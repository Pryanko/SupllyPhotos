package ru.supplyphotos.presentation.fragments.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.presentation.adapters.CategoryAdapter;
import ru.supplyphotos.presentation.presenters.CategoryPresenter;

/**
 * @author Libgo on 20.01.2018.
 */

public class CategoryFragment extends MvpAppCompatFragment {
    //Bind
    @BindView(R.id.category_top_image)
    SimpleDraweeView categoryTopImage;
    @BindView(R.id.category_recycler)
    RecyclerView categoryRecycler;

    private CategoryAdapter categoryAdapter;
    private CategoryPresenter categoryPresenter = App.getAppComponent().getCategoryPresenter();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryAdapter = new CategoryAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        categoryPresenter.setView(this);
        categoryPresenter.createView();
        return view;
    }

    public void startShow(List<ItemCategory> list){
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryAdapter.addCategoryList(list);
        categoryRecycler.setAdapter(categoryAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        categoryPresenter.destroyView();
    }
}
