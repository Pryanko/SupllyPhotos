package ru.supplyphotos.data.db;

import android.util.Log;
import java.util.List;

import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmResults;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author libgo (01.04.2018)
 */
public class DataBaseSource implements RealmDataBase{


    @Override
    public void createTableImageStorage(List<ItemStorageImage> itemStorageImageList) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.insert(itemStorageImageList);
        Log.d("DBASE!!!", String.valueOf(realm.where(ItemStorageImage.class).findAll().size()));
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public RealmResults<ItemStorageImage> getItemDBImage() {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(ItemStorageImage.class).findAll();

    }


}
