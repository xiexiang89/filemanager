package com.edgar.filemanager.video;

import android.content.Context;
import android.database.Cursor;
import android.view.ViewGroup;

import com.edgar.filemanager.MediaFileListAdapter;

public class VideoListAdapter extends MediaFileListAdapter<VideoHolder> {

    public VideoListAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindCursorViewHolder(VideoHolder holder, Cursor cursor, int position) {

    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}