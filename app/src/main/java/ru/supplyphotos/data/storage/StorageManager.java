package ru.supplyphotos.data.storage;


import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import ru.supplyphotos.App;
import ru.supplyphotos.data.db.DataBaseSource;
import ru.supplyphotos.tools.settings.SettingInterface;

import static ru.supplyphotos.constants.Constants.DEFAULT_COUNT_PRINT_IMAGE;
import static ru.supplyphotos.constants.Constants.PREFIX_PATH_IMAGE;

/**
 * @author Libgo on 30.03.2018.
 */
@Singleton
public class StorageManager implements AndroidStorageManger {

    private DataBaseSource dataBaseSource;
    private ContentResolver contentResolver;
    //private SettingInterface settingInterface; //для получения сервис id

    @Inject
    StorageManager(ContentResolver contentResolver, DataBaseSource dataBaseSource) {
        this.contentResolver = contentResolver;
        this.dataBaseSource = dataBaseSource;
    }


    @Override
    public Flowable<List<ItemStorageImage>> getListItemsStorageImage() {
        Log.d("СРАБОТАЛА ПАМЯТЬ", "OK");
        return Flowable.just(getListStorageImage())
                .doAfterNext(itemStorageImages ->
                dataBaseSource.createTableImageStorage(itemStorageImages));

    }


    private List<ItemStorageImage> getListStorageImage() {

        List<ItemStorageImage> imageList = new ArrayList<>();

        final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        Cursor imageCursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null,
                null, orderBy + " DESC");

        if (imageCursor != null) {
            for (int i = 0; i < imageCursor.getCount();i++){
                ItemStorageImage itemStorageImage = new ItemStorageImage();
                itemStorageImage.setId_item(i);
                itemStorageImage.setCountPrint(DEFAULT_COUNT_PRINT_IMAGE);
                itemStorageImage.setSelected(false);
                imageCursor.moveToPosition(i);
                int dataColumnIndex = imageCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                itemStorageImage.setPath(PREFIX_PATH_IMAGE + imageCursor.getString(dataColumnIndex));
                imageList.add(itemStorageImage);
               //Log.d("TAG", itemStorageImage.getPath());
               //Log.d("TAG", String.valueOf(itemStorageImage.getId_item()));
             }

        }
        else {
            return new ArrayList<>();
        }
        imageCursor.close();
        return imageList;
    }


}
