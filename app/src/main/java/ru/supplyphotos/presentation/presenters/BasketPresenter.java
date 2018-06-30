package ru.supplyphotos.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.supplyphotos.presentation.fragments.ContractsFragmentView;

import static ru.supplyphotos.constants.Constants.BASKET_SCREEN;

/**
 * @author Libgo on 10.04.2018.
 */
@InjectViewState
public class BasketPresenter extends MvpPresenter<ContractsFragmentView.BasketView>
        implements BasePresenter.Basket {

    private final Representative representative;

    public BasketPresenter(Representative representative) {

        this.representative = representative;
    }

    @Override
    public void attachView(ContractsFragmentView.BasketView view) {
        super.attachView(view);
        representative.switchTypeScreen(BASKET_SCREEN);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void destroyView() {

    }
}
