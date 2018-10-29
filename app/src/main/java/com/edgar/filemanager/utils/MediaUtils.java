package com.edgar.filemanager.utils;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Edgar on 2018/10/26.
 */
public class MediaUtils {

    private MediaUtils() {}

    public static String getTitle(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE));
    }

    public static String getDisplayName(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME));
    }

    public static long getLastModified(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_MODIFIED)) * 1000;
    }

    public static long getSize(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
    }

    public static Uri getAudioAlbumImageUri(Cursor cursor) {
        final int albumId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
        return ContentUris.withAppendedId(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, albumId);
    }

//    public static Uri getVideoThumb(Cursor cursor) {
//        final int thumbId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.MINI_THUMB_MAGIC));
//        return getVideoThumb(thumbId);
//    }
//
//    public static Uri getVideoThumb(int thumbId) {
//        ContentResolver resolver = FileManagerApplication.contentResolver();
//        final Uri baseUri = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
//        Cursor c = resolver.query(baseUri, PROJECTION, "video_id=" + thumbId, null, null);
//        if (c != null && c.moveToFirst()) {
//            return getThumbUri(c, baseUri,resolver);
//        }
//        Uri blockingUri = baseUri.buildUpon().appendQueryParameter("blocking", "1")
//                .appendQueryParameter("orig_id", String.valueOf(thumbId))
//                .appendQueryParameter("group_id", String.valueOf(0)).build();
//        if (c != null) c.close();
//        c = resolver.query(blockingUri, PROJECTION, null, null, null);
//        if (c != null && c.moveToNext()) {
//            return getThumbUri(c, baseUri, resolver);
//        }
//    }
//
//    private static Uri getThumbUri(
//            Cursor c, Uri baseUri, ContentResolver cr) {
//        long thumbId = c.getLong(0);
//        return ContentUris.withAppendedId(baseUri, thumbId);
//    }

}