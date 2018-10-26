package com.edgar.filemanager.utils;

import android.database.Cursor;
import android.provider.MediaStore;

/**
 * Created by Edgar on 2018/10/26.
 */
public class MediaUtils {

    private MediaUtils() {}

    public static String getDisplayName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME));
    }

    public static long getLastModified(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED)) * 1000;
    }

    public static long getSize(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
    }
}
