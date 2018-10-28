package com.edgar.filemanager.music;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.edgar.filemanager.RefreshListFragment;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

/**
 * Created by Edgar on 2018/10/23.
 */
public class MusicListFragment extends RefreshListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int LOADER_ID = 1;

    private LoaderManager mLoaderManager;
    private MusicListAdapter mMusicListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoaderManager = LoaderManager.getInstance(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMusicListAdapter = new MusicListAdapter(getContext());
        setAdapter(mMusicListAdapter);
        mLoaderManager.initLoader(LOADER_ID,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        getRefreshLayout().setRefreshing(false);
        mMusicListAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mLoaderManager.restartLoader(LOADER_ID,null,this);
    }
}