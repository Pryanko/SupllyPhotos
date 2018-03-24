package ru.supplyphotos.presentation.activities;

import com.arellomobile.mvp.MvpView;

/**
 * @author Libgo on 23.01.2018.
 */

public interface BaseViewActivity extends MvpView {

    void startShow();

    void closeScreen();
}
