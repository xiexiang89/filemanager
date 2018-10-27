package com.edgar.filemanager.fresco;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.edgar.filemanager.utils.UriUtils;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Created by Edgar on 2018/10/27.
 */
public class CustomLocalContentUriFetchProducer extends LocalContentUriFetchProducer {

    private final ContentResolver mContentResolver;

    public CustomLocalContentUriFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory, contentResolver);
        mContentResolver = contentResolver;
    }

    @Override
    protected EncodedImage getEncodedImage(ImageRequest imageRequest) throws IOException {
        final Uri sourceUri = imageRequest.getSourceUri();
        if (UriUtils.isAudioAlbumUri(sourceUri)) {
            return getAudioAlbumImage(sourceUri);
        }
        return super.getEncodedImage(imageRequest);
    }

    private EncodedImage getAudioAlbumImage(Uri uri) throws IOException {
        Cursor cursor = mContentResolver.query(uri, new String[]{MediaStore.Audio.Albums.ALBUM_ART}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToNext();
            final String pathName = cursor.getString(0);
            return getEncodedImage(new FileInputStream(pathName),getLength(pathName));
        } finally {
            cursor.close();
        }
    }

    private static int getLength(String pathname) {
        return pathname == null ? -1 : (int) new File(pathname).length();
    }
}