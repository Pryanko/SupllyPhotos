package ru.supplyphotos.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import okhttp3.internal.http2.Header;
import ru.supplyphotos.App;
import ru.supplyphotos.data.repository.UploadRepository;
import ru.supplyphotos.data.upload.order_item_id.OrderItemId;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;

/**
 * @author Libgo on 03.04.2018.
 */

@InjectViewState
public class UploadPresenter extends MvpPresenter<ContractsFragmentView.UploadView>
        implements BasePresenter.Upload {

    private SettingInterface settingInterface;
    private UploadRepository uploadRepository;
    private Integer i = 0;

    public UploadPresenter() {
        uploadRepository = App.getAppComponent().getUploadRepository();
        settingInterface = App.getAppComponent().getSettingsHelper().getSettingsInterface();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showLoading(true);
        Disposable disposable = uploadRepository.createOrderItem()
                .subscribe(this::createOrderItemId);
        getViewState().setUploadMaxProgress(uploadRepository.getMaxProgressBar());


    }

    private void createOrderItemId(OrderItemId orderItemId){
              settingInterface.openOrderId(true,
                      orderItemId.getData().getOrderItemId());
              Log.d("ORDER ITEM ID", String.valueOf(orderItemId.getData().getOrderItemId()));
        Disposable disposable = uploadRepository.startingUploadImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::OkUpload, this::onError);

    }

    private void OkUpload(ResponseBody responseBody) throws IOException {
        Log.d("Header",responseBody.toString());
        getViewState().setUploadStatus(1);
        i++;
        getViewState().setTextStatus(i, uploadRepository.getMaxProgressBar());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }
}
