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


}
