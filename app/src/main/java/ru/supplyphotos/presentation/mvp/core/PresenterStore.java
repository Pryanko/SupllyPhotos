package ru.supplyphotos.presentation.mvp.core;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import ru.supplyphotos.presentation.mvp.presenter.MvpPresenter;

/**
 * Хранилище презентеров
 *
 * @author Grigoriy Pryamov.
 */
public class PresenterStore {

    private Map<String, MvpPresenter> presenters = new HashMap<>();

    @Inject
    public PresenterStore() {
    }

    public <P extends MvpPresenter> P getPresenter(@NonNull String tag) {
        return (P) presenters.get(tag);
    }

    public void putPresenter(@NonNull String tag, @NonNull MvpPresenter presenter) {
        presenters.put(tag, presenter);
    }

    public void removePresenter(@NonNull String tag) {
        presenters.remove(tag);
    }
}
