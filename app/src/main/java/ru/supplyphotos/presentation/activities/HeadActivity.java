package ru.supplyphotos.presentation.activities;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import ru.supplyphotos.R;

/**
 * @author libgo (05.01.2018)
 */

public class HeadActivity extends MvpAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
    }
}
