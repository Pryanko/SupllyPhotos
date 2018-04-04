package ru.supplyphotos.presentation.fragments.description;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import ru.supplyphotos.presentation.fragments.ContractsFragmentView;

/**
 * @author Libgo on 04.04.2018.
 */


public class DescriptionFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.DescriptionView {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //implements
    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
