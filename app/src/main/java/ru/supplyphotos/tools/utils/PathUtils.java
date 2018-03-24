package ru.supplyphotos.tools.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import ru.supplyphotos.App;

/**
 * @author Libgo on 12.03.2018.
 */

public class PathUtils {

    public static String getRealFilePath(Uri pathUri, ContentResolver contentResolver){

        String path = null;
        String[] paths = { MediaStore.MediaColumns.DATA };
        Cursor cursor = contentResolver.query(pathUri, paths, null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }


}
