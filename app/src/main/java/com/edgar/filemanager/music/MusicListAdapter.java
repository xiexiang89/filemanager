package com.edgar.filemanager.music;

import android.content.Context;
import android.database.Cursor;

import com.edgar.filemanager.MediaCursorAdapter;

/**
 * Created by Edgar on 2018/10/26.
 */
public class MusicListAdapter extends MediaCursorAdapter {

    public MusicListAdapter(Context context, Cursor c) {
        super(context, c, FLAG_REGISTER_CONTENT_OBSERVER);
    }
}
