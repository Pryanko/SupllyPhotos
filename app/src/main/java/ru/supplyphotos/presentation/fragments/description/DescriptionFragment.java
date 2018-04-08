package ru.supplyphotos.presentation.fragments.description;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.presenters.DescriptionPresenter;

/**
 * @author Libgo on 04.04.2018.
 */


public class DescriptionFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.DescriptionView {

    @BindView(R.id.button_choice)
    Button button;
    @BindView(R.id.image_description)
    SimpleDraweeView description_image;
    @BindView(R.id.text_description)
    TextView descriptionText;

    @InjectPresenter
    DescriptionPresenter descriptionPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        ButterKnife.bind(this, view);
        button.setOnClickListener(v -> descriptionPresenter.nextScreen());
        return view;
    }

    //implements
    @Override
    public void startDescription(ItemService itemService){
        description_image.setImageURI(itemService.getImage480());
        descriptionText.setText(itemService.getDescription());
    }


    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {

    }
}
