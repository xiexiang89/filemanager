package com.edgar.filemanager.music;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.edgar.filemanager.MediaCursorAdapter;
import com.edgar.filemanager.R;
import com.edgar.filemanager.utils.MediaUtils;

public class MusicListAdapter extends MediaCursorAdapter {

    public MusicListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getPlaceholderDrawable() {
        return R.drawable.ic_music_placeholder;
    }

    @Override
    protected Uri getMediaImageUri(Cursor cursor) {
        return MediaUtils.getAudioAlbumImageUri(cursor);
    }

    @Override
    protected void onMediaItemClick(Cursor cursor, int position) {
        super.onMediaItemClick(cursor, position);
        MediaUtils.playAudio(mContext, MediaUtils.getPath(cursor));
    }
}