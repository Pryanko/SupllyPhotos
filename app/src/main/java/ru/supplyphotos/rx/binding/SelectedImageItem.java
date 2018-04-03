package ru.supplyphotos.rx.binding;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;
import ru.supplyphotos.design.GalleryCheckBox;

/**
 * @author Libgo on 02.04.2018.
 */
public class SelectedImageItem implements ObservableSource<Boolean>{

    private GalleryCheckBox galleryCheckBox;
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    public SelectedImageItem(GalleryCheckBox galleryCheckBox, ImageView imageView,
                             RelativeLayout relativeLayout) {
        this.galleryCheckBox = galleryCheckBox;
        this.imageView = imageView;
        this.relativeLayout = relativeLayout;
    }


    @Override
    public void subscribe(Observer<? super Boolean> observer) {


         ImageView.OnClickListener imageClick = new ImageView.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(galleryCheckBox.isChecked()){
                     //galleryCheckBox.setChecked(false);
                 }else {
                     galleryCheckBox.setChecked(true, true);
                 }

             }
         };


        GalleryCheckBox.OnCheckedChangeListener onCheckedChangeListener = new GalleryCheckBox.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(GalleryCheckBox checkBox, boolean isChecked) {
                if(isChecked){
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                else {
                    relativeLayout.setVisibility(View.GONE);
                }
                observer.onNext(isChecked);

            }
        };

        observer.onSubscribe(new MainThreadDisposable() {
            @Override
            protected void onDispose() {
                galleryCheckBox.setOnCheckedChangeListener(null);
                imageView.setOnClickListener(null);
            }
        });

        galleryCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        imageView.setOnClickListener(imageClick);
    }
}
