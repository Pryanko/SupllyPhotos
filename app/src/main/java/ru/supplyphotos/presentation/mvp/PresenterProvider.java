package ru.supplyphotos.presentation.mvp;

import ru.supplyphotos.presentation.mvp.presenter.MvpPresenter;

/**
 * Фабрика презентеров
 *
 * @author Grigoriy Pryamov.
 */
public interface PresenterProvider<P extends MvpPresenter> {
    P providePresenter();
}
