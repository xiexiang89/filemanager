package com.edgar.filemanager.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;

import com.edgar.filemanager.model.MediaFile;

import java.io.File;

/**
 * Created by Edgar on 2018/10/26.
 */
public class MediaUtils {

    private MediaUtils() {}

    public static String getPath(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
    }

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

    public static long getVideoDuration(Cursor cursor) {
        return getMediaDuration(cursor, MediaStore.Video.VideoColumns.DURATION);
    }

    public static long getMediaDuration(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndexOrThrow(columnName));
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

    public static void playAudio(Context context, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(UriUtils.fromFile(fileName),"audio/*");
        startSystemActivity(context, intent);
    }

    public static void playVideo(Context context, String fileName) {
        startSystemActivity(context, new Intent(Intent.ACTION_VIEW).setDataAndType(UriUtils.fromFile(fileName),"video/*"));
    }

    private static void startSystemActivity(Context context, Intent intent) {
        StrictMode.VmPolicy vmPolicy = null;
        if (Build.VERSION.SDK_INT >= 24) {
            vmPolicy = StrictMode.getVmPolicy();
            StrictMode.VmPolicy tempVmPolicy = (new android.os.StrictMode.VmPolicy.Builder()).penaltyLog().build();
            StrictMode.setVmPolicy(tempVmPolicy);
        }
        context.startActivity(intent);
        if (vmPolicy != null) {
            StrictMode.setVmPolicy(vmPolicy);
        }
    }

}