package ru.supplyphotos.presentation.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import ru.supplyphotos.data.repository.UploadRepository;
import ru.supplyphotos.data.upload.order_item_id.OrderItemId;
import ru.supplyphotos.presentation.fragments.ContractsFragmentView;
import ru.supplyphotos.tools.settings.SettingInterface;
import ru.supplyphotos.tools.settings.SettingsHelper;

/**
 * @author Libgo on 03.04.2018.
 */

@InjectViewState
public class UploadPresenter extends MvpPresenter<ContractsFragmentView.UploadView>
        implements BasePresenter.Upload {

    private final SettingInterface settingInterface;
    private final UploadRepository uploadRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Integer i = 0;

    public UploadPresenter(UploadRepository uploadRepository,
                           SettingsHelper settingsHelper) {
        this.settingInterface = settingsHelper.getSettingsInterface();
        this.uploadRepository = uploadRepository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showLoading(true);
        getViewState().setTextStatus(i, uploadRepository.getMaxProgressBar());
        getViewState().setUploadMaxProgress(uploadRepository.getMaxProgressBar());

        compositeDisposable.add(uploadRepository.createOrderItem()
                .subscribe(this::createOrderItemId));

    }

    private void createOrderItemId(OrderItemId orderItemId) {
        settingInterface.openOrderId(true,
                orderItemId.getData().getOrderItemId());
        Log.d("ORDER ITEM ID", String.valueOf(orderItemId.getData().getOrderItemId()));


        compositeDisposable.add(uploadRepository.startingUploadImage().parallel().runOn(Schedulers.io())
                .sequential()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::OkUpload, this::onError));


    }

    private void OkUpload(ResponseBody responseBody) throws IOException {
        Log.d("Header", responseBody.toString());
        getViewState().setUploadStatus(1);
        i++;
        if (i.equals(uploadRepository.getMaxProgressBar())) {
            getViewState().showLoading(false);
            getViewState().setCompleteText("Спасибо, все ок!");
        } else {
            getViewState().setTextStatus(i, uploadRepository.getMaxProgressBar());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("LOGS_SCREEN", throwable.getMessage());


    }

    @Override
    public void destroyView() {

    }
}
