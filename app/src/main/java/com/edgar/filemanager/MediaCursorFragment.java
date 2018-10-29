package com.edgar.filemanager;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

/**
 * Created by Edgar on 2018/10/29.
 */
public abstract class MediaCursorFragment<Adapter extends MediaCursorAdapter> extends RefreshListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    protected LoaderManager mLoaderManager;
    protected MediaCursorAdapter mMediaCursorAdapter;
    private String mSortOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoaderManager = LoaderManager.getInstance(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMediaCursorAdapter = onCreateMediaCursorAdapter();
        setAdapter(mMediaCursorAdapter);
        mLoaderManager.initLoader(getLoaderId(), null,this);
    }

    public void setSortOrder(String sortOrder) {
        mSortOrder = sortOrder;
    }

    protected abstract Adapter onCreateMediaCursorAdapter();
    protected abstract int getLoaderId();
    protected abstract Uri getContentUri();

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(), getContentUri(), null,null,null,mSortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        getRefreshLayout().setRefreshing(false);
        mMediaCursorAdapter.changeCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mLoaderManager.restartLoader(getLoaderId(), null,this);
    }
}