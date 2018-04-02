package ru.supplyphotos.data.repository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.supplyphotos.App;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.manuals.Manual;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.db.DataBaseSource;
import ru.supplyphotos.data.db.RealmDataBase;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.data.storage.StorageManager;
import ru.supplyphotos.rx.RxNetwork;

/**
 * @author Libgo on 15.03.2018.
 */

public class AppRepository implements BaseAppRepository {

    private StorageManager storageManager;
    private DataBaseSource dataBaseSource;

    public AppRepository() {
        this.storageManager = App.getAppComponent().getStorageManager();
        this.dataBaseSource = App.getAppComponent().getDataBaseSource();
    }

    @Override
    public Observable<Manual> getGuides() {
        return RxNetwork.getGuides();
    }

    @Override
    public Observable<List<ItemCategory>> getListCategory(){
        return RxNetwork.getListCategory();
    }

    public Observable<List<ItemService>> getListService(Integer service_id){
        return RxNetwork.getListService(service_id);
    }


    @Override
    public Flowable<List<ItemStorageImage>> getImageFlowable(){

        if(dataBaseSource.getFillImagesTable()){
            return storageManager.getListItemsStorageImage()
                    .subscribeOn(Schedulers.io());
        } else {
            return dataBaseSource.getItemDBImage();
        }


    }

    public RealmDataBase.UpdateTable getTouchManager(){
        return dataBaseSource.getTouchManager();
    }




}
