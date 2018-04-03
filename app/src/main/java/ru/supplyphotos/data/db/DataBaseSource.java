package ru.supplyphotos.data.db;

import android.util.Log;
import java.util.List;
import java.util.Objects;


import io.reactivex.Flowable;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.supplyphotos.data.storage.ItemStorageImage;

/**
 * @author libgo (01.04.2018)
 */
public class DataBaseSource implements RealmDataBase.CreateGetTable, RealmDataBase.UpdateTable{

    private RealmConfiguration configuration = new RealmConfiguration.Builder()
            .name("table_item_image.realm")
            .schemaVersion(0)
            .build();

    public RealmDataBase.UpdateTable getTouchManager(){
        return this;
    }


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
        return Flowable.just(realm.copyFromRealm(query.findAllAsync()));


    }

    @Override
    public boolean getFillImagesTable(){
        Realm realm = Realm.getInstance(configuration);
        return realm.where(ItemStorageImage.class).findAll().size() == 0;
    }


    @Override
    public void updateCountPrintImage(Integer item_id, Integer count) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        ItemStorageImage itemStorageImage = realm.where(ItemStorageImage.class)
                .equalTo("id_item", item_id).findFirst();
        Objects.requireNonNull(itemStorageImage).setCountPrint(count);
        realm.commitTransaction();
        realm.close();


    }

    @Override
    public void switchSelectedItemImage(Integer item_id, Boolean isSelectedItem) {
        Realm realm = Realm.getInstance(configuration);
        realm.beginTransaction();
        ItemStorageImage itemStorageImage = realm.where(ItemStorageImage.class)
                .equalTo("id_item", item_id).findFirst();
        Objects.requireNonNull(itemStorageImage).setSelected(isSelectedItem);
        realm.commitTransaction();
        realm.close();
    }

    public RealmResults<ItemStorageImage> getSelectedList(){
        Realm realm = Realm.getInstance(configuration);
        return realm.where(ItemStorageImage.class).equalTo("isSelected", true).findAll();

    }
}
