package ru.supplyphotos.presentation.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.supplyphotos.App;
import ru.supplyphotos.R;
import ru.supplyphotos.presentation.presenters.SplashPresenter;

/**
 * @author libgo (05.01.2018)
 */

public class SplashActivity extends MvpAppCompatActivity implements BaseViewActivity {


    @BindView(R.id.splashView)
    ImageView imageView;

    @InjectPresenter
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

    }

    @Override
    public void startShow() {
        imageView.animate().alpha(1.0f).setDuration(600).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility(View.VISIBLE);
                splashPresenter.destroyView();
            }
        });
    }

    @Override
    public void closeScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}
