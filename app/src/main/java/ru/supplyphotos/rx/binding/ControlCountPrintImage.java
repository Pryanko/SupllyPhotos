package ru.supplyphotos.rx.binding;

import android.view.View;
import android.widget.TextView;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import ru.supplyphotos.design.GalleryCheckBox;

import static ru.supplyphotos.constants.Constants.DEFAULT_COUNT_PRINT_IMAGE;

/**
 * @author Libgo on 03.04.2018.
 */
public class ControlCountPrintImage implements ObservableSource<Integer> {


    private TextView positiveCount;
    private TextView negativeCount;
    private TextView textCount;
    private GalleryCheckBox galleryCheckBox;

    public ControlCountPrintImage(TextView positiveCount, TextView negativeCount,
                                  TextView textCount, GalleryCheckBox galleryCheckBox) {
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
        this.textCount = textCount;
        this.galleryCheckBox = galleryCheckBox;
    }






    @Override
    public void subscribe(Observer<? super Integer> observer) {


        TextView.OnClickListener positiveListener = v -> {

            String s = String.valueOf(textCount.getText());
            textCount.setText(String.valueOf(Integer.parseInt(s) + DEFAULT_COUNT_PRINT_IMAGE));
            observer.onNext(Integer.parseInt(String.valueOf(textCount.getText())));
        };

        TextView.OnClickListener negativeListener = v -> {

            if(Integer.parseInt(String.valueOf(textCount.getText())) > DEFAULT_COUNT_PRINT_IMAGE){
                String s = String.valueOf(textCount.getText());
                textCount.setText(String.valueOf(Integer.parseInt(s) - DEFAULT_COUNT_PRINT_IMAGE));
                observer.onNext(Integer.parseInt(String.valueOf(textCount.getText())));
            }
            else {
                galleryCheckBox.setChecked(false, true);
            }

        };

        observer.onSubscribe(new MainThreadDisposable() {

            @Override
            protected void onDispose() {
                positiveCount.setOnClickListener(null);
                negativeCount.setOnClickListener(null);
            }
        });

        positiveCount.setOnClickListener(positiveListener);
        negativeCount.setOnClickListener(negativeListener);

    }



}
