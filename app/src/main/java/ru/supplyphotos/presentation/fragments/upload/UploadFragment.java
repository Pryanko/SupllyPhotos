package ru.supplyphotos.presentation.fragments.upload;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.presentation.presenters.UploadPresenter;

/**
 * @author Libgo on 03.04.2018.
 */
public class UploadFragment extends MvpAppCompatFragment
        implements ContractsFragmentView.UploadView {

    @BindView(R.id.progressBarUpload)
    ProgressBar progressBar;
    @BindView(R.id.textProgressBar)
    TextView textView;


    @InjectPresenter
    UploadPresenter uploadPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    //implements

    @Override
    public void setUploadMaxProgress(Integer endUpload) {
       progressBar.setMax(endUpload);
    }

    @Override
    public void setUploadStatus(Integer intStatus) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(intStatus, true);
        }
        else {
            progressBar.setProgress(intStatus);
        }
    }

    @Override
    public void setTextStatus(Integer intStatus, Integer endUpload) {
         textView.setText(String.format("Загружено %sиз %s", String.valueOf(intStatus), String.valueOf(endUpload)));
    }



    @Override
    public void onError() {

    }

    @Override
    public void showLoading(boolean loading) {
        if(loading)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }

    }


}
