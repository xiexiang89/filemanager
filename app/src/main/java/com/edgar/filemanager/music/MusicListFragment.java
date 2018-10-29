package com.edgar.filemanager.music;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.edgar.filemanager.MediaCursorFragment;
import com.edgar.filemanager.constant.LoaderConstant;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

/**
 * Created by Edgar on 2018/10/23.
 */
public class MusicListFragment extends MediaCursorFragment<MusicListAdapter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoaderManager = LoaderManager.getInstance(this);
        setSortOrder(MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected MusicListAdapter onCreateMediaCursorAdapter() {
        return new MusicListAdapter(getContext());
    }

    @Override
    protected int getLoaderId() {
        return LoaderConstant.MUSIC_LOADER_ID;
    }

    @Override
    protected Uri getContentUri() {
        return MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }
}