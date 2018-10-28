package com.edgar.filemanager.music;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.edgar.filemanager.MediaFileListAdapter;
import com.edgar.filemanager.R;
import com.edgar.filemanager.utils.MediaUtils;

public class MusicFileListAdapter extends MediaFileListAdapter {

    public MusicFileListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getMediaPlaceholderDrawable() {
        return R.drawable.ic_music_placeholder;
    }

    @Override
    protected Uri getImageUri(Cursor cursor) {
        return MediaUtils.getAudioAlbumImageUri(cursor);
    }
}
