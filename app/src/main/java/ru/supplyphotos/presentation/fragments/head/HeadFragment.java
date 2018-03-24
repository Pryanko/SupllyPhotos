package ru.supplyphotos.presentation.fragments.head;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.MvpAppCompatFragment;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.fragments.category.CategoryFragment;


/**
 * @author Libgo on 24.01.2018.
 */

public class HeadFragment extends MvpAppCompatFragment implements ContractsFragmentView.HeadView{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head, container, false);
        ButterKnife.bind(this,view);
        getChildFragmentManager().beginTransaction().replace(R.id.frame_for_child_fragments, new CategoryFragment()).commit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
