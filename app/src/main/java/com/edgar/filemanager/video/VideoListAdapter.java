package com.edgar.filemanager.video;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.edgar.filemanager.MediaCursorAdapter;
import com.edgar.filemanager.utils.MediaUtils;
import com.edgar.filemanager.utils.UriUtils;
import com.edgar.filemanager.widget.MediaCursorItemViewHolder;

public class VideoListAdapter extends MediaCursorAdapter {

    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    protected MediaCursorItemViewHolder onCreateMediaViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new VideoViewHolder(inflater, parent);
    }

    @Override
    protected Uri getMediaImageUri(Cursor cursor) {
        return UriUtils.fromFile(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATA)));
    }

    @Override
    protected void onMediaItemClick(Cursor cursor, int position) {
        super.onMediaItemClick(cursor, position);
        MediaUtils.playVideo(mContext, MediaUtils.getPath(cursor));
    }
}