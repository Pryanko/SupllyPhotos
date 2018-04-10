package ru.supplyphotos.presentation.fragments.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.presenters.RegisterPresenter;

/**
 * @author Libgo on 09.04.2018.
 */
public class RegisterFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.RegisterView {

    @BindView(R.id.button_register_next)
    Button nextButton;

    @InjectPresenter
    RegisterPresenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);
         nextButton.setOnClickListener(v -> registerPresenter.nextScreen());
        return view;
    }




    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
