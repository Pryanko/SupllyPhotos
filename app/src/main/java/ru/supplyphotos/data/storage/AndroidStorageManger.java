package ru.supplyphotos.data.storage;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author Libgo on 30.03.2018.
 */
public interface AndroidStorageManger {


    Flowable<List<ItemStorageImage>> getListItemsStorageImage();


}
