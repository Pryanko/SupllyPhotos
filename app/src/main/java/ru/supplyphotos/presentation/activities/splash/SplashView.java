package ru.supplyphotos.presentation.activities.splash;

import ru.supplyphotos.data.model.error.UserError;
import ru.supplyphotos.presentation.mvp.view.MvpView;

/**
 * @author Grigoriy Pryamov.
 */
public interface SplashView extends MvpView {

    void setButtonVisible(boolean buttonVisible);

    void setLoading(boolean loading);

    void showErrorMessage(UserError userError);
}
