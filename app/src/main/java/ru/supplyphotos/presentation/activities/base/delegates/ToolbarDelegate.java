package ru.supplyphotos.presentation.activities.base.delegates;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.R;
import ru.supplyphotos.di.ActivityScope;
import ru.supplyphotos.presentation.activities.base.BaseActivity;

/**
 * Делегат для работы с toolbar
 *
 * @author Grigoriy Pryamov.
 */
@ActivityScope
public class ToolbarDelegate {

    private final BaseActivity activity;
    private OnBasketBtnClickedListener onBasketBtnClickedListener;
    private OnActionBtnClickedListener onActionBtnClickedListener;
    //region views
    @BindView(R.id.basketView)
    ImageView basketView;
    @BindView(R.id.main_toolBar)
    Toolbar toolbar;
    //endregion

    @Inject
    ToolbarDelegate(@NonNull BaseActivity activity) {
        this.activity = activity;
        ButterKnife.bind(this.activity);
    }

    public void init() {
        ButterKnife.bind(this, activity);
        activity.setSupportActionBar(toolbar);
//        ButterKnife.bind(activity);
//        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
//        toolbar.setTitleTextColor(Color.parseColor("#ffbe75");
//        toolbar.setActionIcon(getResources().getDrawable(R.drawable.ic_account));
//        getSupportActionBar().setTitle("I Love");
    }

    public void addOnBasketBtnClickedListener(OnBasketBtnClickedListener onBasketBtnClickedListener) {
        this.onBasketBtnClickedListener = onBasketBtnClickedListener;
        basketView.setOnClickListener(v -> onBasketBtnClickedListener.onBasketBtnClicked());
    }

    public void addOnActionBtnClickedListener(OnActionBtnClickedListener onActionBtnClickedListener) {
        this.onActionBtnClickedListener = onActionBtnClickedListener;
    }

    public void setBackgroundColor(@ColorRes int resId) {
        toolbar.setBackgroundResource(resId);
    }

    public void setActionIcon(@DrawableRes int resId) {
        toolbar.setNavigationIcon(resId);
    }

    public void setActionIcon(@Nullable Drawable icon) {
        toolbar.setNavigationIcon(icon);
    }

    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setToolbarVisible(boolean toolbarVisible) {
        if (toolbarVisible) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    /**
     * Взаимодействие
     */
    public interface OnBasketBtnClickedListener {
        void onBasketBtnClicked();
    }

    public interface OnActionBtnClickedListener {
        void actionBtnClicked();
    }
}
