package ru.supplyphotos.presentation;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import ru.supplyphotos.R;

/**
 * Базовая кнопка приложения
 *
 * @author Grigoriy Pryamov.
 */
public class CustomButton extends AppCompatButton {

    public CustomButton(Context context) {
        this(context, null);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
