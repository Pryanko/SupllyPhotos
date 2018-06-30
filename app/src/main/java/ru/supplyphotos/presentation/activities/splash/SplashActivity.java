package ru.supplyphotos.presentation.activities.splash;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.sumplyphotos.logger.Logger;
import ru.sumplyphotos.logger.LoggerFactory;
import ru.supplyphotos.R;
import ru.supplyphotos.data.model.error.UserError;
import ru.supplyphotos.presentation.activities.base.MvpActivity;

/**
 * @author libgo (05.01.2018)
 */

public class SplashActivity extends MvpActivity implements SplashView {

    private final Logger logger = LoggerFactory.getLogger(SplashActivity.class);

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    //region Di
    SplashScreenComponent screenComponent;
    SplashComponent component;
    @Inject
    Navigator navigator;
    //endregion
    //region Views
    @BindView(R.id.nextButton)
    Button nextButton;
    //endregion
    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logger.trace("onCreate");
        screenComponent = getScreenComponent();
        component = screenComponent.splashComponent();
        component.inject(this);
        super.onCreate(savedInstanceState);
        presenter = getMvpDelegate().getPresenter(component::splashPresenter, SplashPresenter.class);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        nextButton.setOnClickListener(v -> presenter.onNextBtnClicked());
        presenter.initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigator.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigator.onPause();
    }

    //region implements
    @Override
    public void setButtonVisible(boolean buttonVisible) {
        logger.trace("setButtonVisible");
        if (buttonVisible) {
            logger.trace("startShow");
            nextButton.animate().alpha(1.0f).setDuration(850).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    nextButton.setVisibility(View.VISIBLE);
                }
            });
        } else {
            nextButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void setLoading(boolean loading) {

    }

    @Override
    public void showErrorMessage(UserError userError) {

    }
    //endregion

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return screenComponent;
    }

    public SplashScreenComponent getScreenComponent() {
        Object saved = getLastCustomNonConfigurationInstance();
        if (saved == null) {
            return appComponent().splashScreenComponent();
        } else {
            return (SplashScreenComponent) saved;
        }
    }
}
