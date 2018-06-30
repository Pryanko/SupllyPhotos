package ru.supplyphotos.presentation.activities.head;

import javax.inject.Inject;

import ru.supplyphotos.presentation.mvp.viewstate.BaseMvpViewState;

/**
 * @author Grigoriy Pryamov.
 */
class HeadViewState extends BaseMvpViewState<HeadView> implements HeadView{

    @Inject
    public HeadViewState() {
    }

    @Override
    protected void onViewAttached(HeadView view) {

    }

    @Override
    protected void onViewDetached(HeadView view) {

    }
}
