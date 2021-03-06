package com.edgar.filemanager.utils;

import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by Edgar on 2018/10/27.
 */
public class UriUtils {

    public static boolean isAudioAlbumUri(Uri uri) {
        final String uriString = uri.toString();
        return uriString.startsWith(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI.toString())
                || uriString.startsWith(MediaStore.Audio.Albums.INTERNAL_CONTENT_URI.toString());
    }

    public static Uri fromFile(String path) {
        return Uri.fromFile(new File(path));
    }
}
