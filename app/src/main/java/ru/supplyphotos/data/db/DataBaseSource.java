package ru.supplyphotos.data.db;

import android.util.Log;
import java.util.List;


import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author libgo (01.04.2018)
 */
public class DataBaseSource implements RealmDataBase{

    private RealmConfiguration configuration = new RealmConfiguration.Builder()
            .name("table_item_image.realm")
            .schemaVersion(0)
            .build();


    @Override
    public void createTableImageStorage(List<ItemStorageImage> itemStorageImageList) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        realm.insert(itemStorageImageList);
        Log.d("DBASE!!!", String.valueOf(realm.where(ItemStorageImage.class).findAll().size()));
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public Flowable<List<ItemStorageImage>> getItemDBImage() {
        Realm realm = Realm.getInstance(configuration);
        RealmQuery<ItemStorageImage> query = realm.where(ItemStorageImage.class);
        Log.d("СРАБОТАЛА БАЗА", "OK");
        return Flowable.just(realm.copyFromRealm(query.findAllAsync()));


    }

    @Override
    public boolean getFillImagesTable(){
        Realm realm = Realm.getInstance(configuration);
        return realm.where(ItemStorageImage.class).findAll().size() == 0;
    }


}
