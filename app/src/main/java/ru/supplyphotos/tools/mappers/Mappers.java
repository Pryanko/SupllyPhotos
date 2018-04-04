package ru.supplyphotos.tools.mappers;


import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.supplyphotos.data.answers.category.Category;
import ru.supplyphotos.data.answers.category.ItemCategory;
import ru.supplyphotos.data.answers.services.ItemService;
import ru.supplyphotos.data.answers.services.Services;
import ru.supplyphotos.data.answers.start_login.DeviceToken;
import ru.supplyphotos.data.answers.start_login.StartToken;
import ru.supplyphotos.data.storage.ItemStorageImage;
import ru.supplyphotos.data.storage.UploadFile;
import ru.supplyphotos.data.upload.ImageFile;
import ru.supplyphotos.data.upload.PhotoIdFile;
import ru.supplyphotos.data.upload.cloud_upload_url.UploadUrl;
import ru.supplyphotos.data.upload.order_id.OrderId;
import ru.supplyphotos.data.upload.order_item_id.OrderItemId;
import ru.supplyphotos.data.upload.photo_id.PhotoId;

/**
 * @author libgo on 14.01.2018.
 */

public class Mappers {

     public static DeviceToken mapDeviceToken(StartToken startToken){
         return startToken.getDeviceToken();
     }

     public static List<ItemCategory> mapListCategory(Category category){
        return category.getListCategory();
    }

    public static List<ItemService> mapListCategory(Services itemService){
        return itemService.getData();
    }

    public static List<PhotoIdFile> filterListZip(List<UploadUrl> uploadUrls, List<File> files) {
        List<PhotoIdFile> photoIdFiles = new ArrayList<>();

        for (int i = 0; i < uploadUrls.size(); i++){
            PhotoIdFile photoIdFile = new PhotoIdFile();
            photoIdFile.setUploadUrl(uploadUrls.get(i).getData().getUrl());
            photoIdFile.setFile(files.get(i));
            photoIdFiles.add(photoIdFile);
        }
                                            return photoIdFiles;

    }

    public static List<File> mapFileList(List<ItemStorageImage> list){
         List<File> files = new ArrayList<>();
         for (ItemStorageImage itemStorageImage : list){
             Log.d("ПУТЬ ФАЙЛА", itemStorageImage.getPath());
             files.add(new File(itemStorageImage.getPath().substring(8)));
         }
         return files;
    }




    public static List<ImageFile> mapListImageSelected(Integer orderItemId,
                                             List<ItemStorageImage> itemStorageImages){
         List<ImageFile> imageFiles = new ArrayList<>();
         for (ItemStorageImage itemStorageImage : itemStorageImages){
             ImageFile imageFile = new ImageFile();
             imageFile.setCountPrint(itemStorageImage.getCountPrint());
             imageFile.setPath(itemStorageImage.getPath());
             imageFile.setFile(new File(itemStorageImage.getPath()));
             imageFile.setNameImage(imageFile.getFile().getName());
             imageFile.setItemOrderId(orderItemId);
             imageFiles.add(imageFile);
         }
         return imageFiles;
    }

    public static boolean filterNotNull(List<ItemStorageImage> list){
        return list.size() != 0;
    }

    //Upload
    public  static Integer mapPhotoId(PhotoId photoId){
        return  photoId.getData().getPhotoId();

    }

    public static Integer mapOrderId(OrderId orderId){
        return orderId.getData().getOrderId();
    }

    public static String getRealPathFromURI (Uri contentUri, ContentResolver contentResolver) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = contentResolver.query(contentUri, proj, null, null,
                null);
        if (Objects.requireNonNull(cursor).moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public static List<ImageFile> mapImageFile(List<String> listPath){
        List<ImageFile> imageFiles = new ArrayList<>();
        for(String s : listPath){
            ImageFile imageFile = new ImageFile();
            File file = new File(s);
            imageFile.setFile(file);
            imageFile.setPath(s);
            imageFile.setNameImage(file.getName());
            imageFiles.add(imageFile);
        }
        return imageFiles;
    }

    public static PhotoIdFile zipPhotoIdFile (ImageFile imageFile, PhotoId photoId){
        PhotoIdFile photoIdFile = new PhotoIdFile();
        photoIdFile.setFile(imageFile.getFile());
        photoIdFile.setPhoto_id(photoId.getData().getPhotoId());
        return photoIdFile;

    }

    public static PhotoIdFile mapPhotoUrlFile(UploadUrl uploadUrl, File file){
        PhotoIdFile photoIdFile = new PhotoIdFile();
        photoIdFile.setUploadUrl(uploadUrl.getData().getUrl());
        photoIdFile.setFile(file);
        return photoIdFile;
    }

}
