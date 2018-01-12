package ru.supplyphotos.presentation.fragments.manuals;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.presenters.ManualPresenter;

/**
 * @autor user on 12.01.2018.
 */

public class PageManual extends MvpAppCompatFragment {

    //BindViews
    @BindView(R.id.header_manual_text)
    TextView headerManualText;
    @BindView(R.id.manual_text)
    TextView manualText;
   // @BindView(R.id.manual_view)
   // SimpleDraweeView manualView;

    private int pageNumber;
    ManualPresenter manualPresenter = App.getAppComponent().getManualPresenter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.fragment_page_manual, container, false);
        ButterKnife.bind(this,view);
        headerManualText.setText("HALLO");
        Log.d("PAGE1", String.valueOf(pageNumber));
        Log.d("PageModel", manualPresenter.getManual(pageNumber).getHeader());
        headerManualText.setText(manualPresenter.getManual(pageNumber).getHeader());
        manualText.setText(manualPresenter.getManual(pageNumber).getInfoText());
        startShow();
        return  view;

    }

    private void startShow(){
        Log.d("PageModel", manualPresenter.getManual(pageNumber).getHeader());
        headerManualText.setText(manualPresenter.getManual(pageNumber).getHeader());
        manualText.setText(manualPresenter.getManual(pageNumber).getInfoText());
       // manualView.setImageURI(manualPresenter.getManual(pageNumber).getImage());
    }
}
