package ru.supplyphotos.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import ru.supplyphotos.App;
import ru.supplyphotos.data.repository.UploadRepository;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;

/**
 * @author Libgo on 03.04.2018.
 */

@InjectViewState
public class UploadPresenter extends MvpPresenter<ContractsFragmentView.UploadView>
        implements BasePresenter.Upload {


    private UploadRepository uploadRepository;

    public UploadPresenter() {
        uploadRepository = App.getAppComponent().getUploadRepository();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showLoading(true);
        Disposable disposable = uploadRepository.startingUploadImage()
                .subscribe(this::OkUpload);


    }

    private void OkUpload(ResponseBody responseBody){
        Log.d("Header", responseBody.toString());
    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {

    }
}
