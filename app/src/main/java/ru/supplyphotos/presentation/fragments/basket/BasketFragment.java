package ru.supplyphotos.presentation.fragments.basket;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.presenters.BasketPresenter;

/**
 * @author Libgo on 10.04.2018.
 */
public class BasketFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.BasketView {

    @InjectPresenter
    BasketPresenter basketPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
