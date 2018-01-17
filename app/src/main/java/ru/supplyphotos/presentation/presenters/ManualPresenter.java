package ru.supplyphotos.presentation.presenters;


import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.supplyphotos.R;
import ru.supplyphotos.data.answers.manuals.Guide;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.network.ApiService;
import ru.supplyphotos.presentation.fragments.manuals.ManualFragment;
import ru.supplyphotos.rx.RxNetwork;


/**
 * @autor user on 12.01.2018.
 */

public class ManualPresenter implements BasePresenter {

    private ManualFragment manualView;
    private List<Guide> guides = new ArrayList<>();
    private int itemNumber;

    public void setView(ManualFragment manualFragment){
        this.manualView = manualFragment;
    }

    @Override
    public void createView() {
        if(getFillList()) {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(RxNetwork.getGuides()
                    .subscribe(this::addManualList, this::handleError)
            );
        }else {
            playShow();
        }
    }
    
    @Override
    public void playShow() {
        manualView.startShow(getSizeListManual(), itemNumber, guides);

    }

    @Override
    public void onError() {

    }

    @Override
    public void destroyView() {
        manualView = null;
    }



    private void addManualList(Manual manual){
        this.guides = manual.getData();
        playShow();
    }

    private Integer getSizeListManual(){
        return guides.size();
    }

    private Boolean getFillList(){
        if(getSizeListManual() == 0){
            return true;
        }
        else {
            return false;
        }
    }

    private void handleError(Throwable throwable) {
        //Обработкой займемся поздней)
    }
    
    public void missManual(){
        manualView.alertShow(manualView.getResources().getString(R.string.alert_show_manual_title),
                manualView.getResources().getString(R.string.alert_show_manual_message),
                manualView.getResources().getString(R.string.alert_show_manual_positive_button),
                manualView.getResources().getString(R.string.alert_show_manual_negative_button));
    }

}
