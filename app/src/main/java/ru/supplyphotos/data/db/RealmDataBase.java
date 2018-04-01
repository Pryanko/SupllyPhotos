package ru.supplyphotos.data.db;

import java.util.List;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author libgo (01.04.2018)
 */
public interface RealmDataBase {


    void createTableImageStorage(List<ItemStorageImage> itemStorageImageList);

    RealmResults<ItemStorageImage> getItemDBImage();

}
