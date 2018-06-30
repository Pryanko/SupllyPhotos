package ru.supplyphotos.presentation.activities.splash;

import javax.inject.Inject;

import ru.supplyphotos.data.model.error.UserError;
import ru.supplyphotos.presentation.mvp.viewstate.BaseMvpViewState;

/**
 * @author Grigoriy Pryamov.
 */
class SplashViewState extends BaseMvpViewState<SplashView> implements SplashView {

    private boolean buttonVisible;

    @Inject
    SplashViewState() {
    }

    @Override
    protected void onViewAttached(SplashView view) {
        view.setButtonVisible(buttonVisible);
    }

    @Override
    protected void onViewDetached(SplashView view) {

    }

    @Override
    public void setButtonVisible(boolean buttonVisible) {
        this.buttonVisible = buttonVisible;
        forEachView(view -> view.setButtonVisible(this.buttonVisible));
    }

    @Override
    public void setLoading(boolean loading) {

    }

    @Override
    public void showErrorMessage(UserError userError) {

    }
}
