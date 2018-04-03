package ru.supplyphotos.rx.binding;


import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.supplyphotos.design.GalleryCheckBox;

/**
 * @author Libgo on 02.04.2018.
 */
public class RxViews {

    public static Observable<Boolean> clicksSelectedImageItem(GalleryCheckBox galleryCheckBox,
                                                              ImageView imageView,
                                                              RelativeLayout relativeLayout) {

        return Observable.unsafeCreate(new SelectedImageItem(galleryCheckBox, imageView,
                relativeLayout))
                .observeOn(AndroidSchedulers.mainThread());

    }


    public static Observable<Integer> clicksControlImagePrint(TextView positiveCount,
                                                              TextView negativeCount,
                                                              TextView textCount,
                                                              GalleryCheckBox galleryCheckBox) {

        return Observable.unsafeCreate(new ControlCountPrintImage(positiveCount, negativeCount,
                textCount, galleryCheckBox))
                .observeOn(AndroidSchedulers.mainThread());

    }


}
