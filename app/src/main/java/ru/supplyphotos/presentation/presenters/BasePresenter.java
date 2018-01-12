package ru.supplyphotos.presentation.presenters;

/**
 * @author or libgo on 12.01.2018.
 */

public interface BasePresenter  {

    void createView();

    void playShow();

    void onError();

    void destroyView();

}
