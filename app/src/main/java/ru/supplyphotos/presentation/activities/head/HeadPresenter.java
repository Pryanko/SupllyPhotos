package ru.supplyphotos.presentation.activities.head;

import javax.inject.Inject;

import ru.supplyphotos.presentation.mvp.presenter.BaseMvpViewStatePresenter;

/**
 * @author Grigoriy Pryamov.
 */
public class HeadPresenter extends BaseMvpViewStatePresenter<HeadView, HeadViewState> {


    @Inject
    HeadPresenter(HeadViewState viewState) {
        super(viewState);
    }

    @Override
    protected void onInitialize() {

    }

    public void onBasketBtnClicked() {

    }
}
