package ru.supplyphotos.data.db;

import java.util.List;

import io.reactivex.Flowable;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author libgo (01.04.2018)
 */
public interface RealmDataBase {

    interface CreateGetTable {

        void createTableImageStorage(List<ItemStorageImage> itemStorageImageList);

        Flowable<List<ItemStorageImage>> getItemDBImage();

        boolean getFillImagesTable();
    }


    interface UpdateTable {

        void updateCountPrintImage(Integer item_id, Integer count);

        void switchSelectedItemImage(Integer item_id, Boolean isSelectedItem);

    }
}
