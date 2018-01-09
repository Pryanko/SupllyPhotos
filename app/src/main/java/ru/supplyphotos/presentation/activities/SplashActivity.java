package ru.supplyphotos.presentation.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import ru.supplyphotos.R;

/**
 * @author libgo (05.01.2018)
 */

public class SplashActivity extends MvpAppCompatActivity {

    private ImageView imageView;
    final Intent intent = new Intent(this, HeadActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView) findViewById(R.id.splashView);
        imageView.setVisibility(View.VISIBLE);
        imageView.animate().alpha(1.0f).setDuration(1600).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setVisibility(View.VISIBLE);
                startActivity(intent);
                finish();
            }
        });
    }
}
