package com.edgar.filemanager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.edgar.filemanager.widget.MediaCursorItemViewHolder;
import com.edgar.filemanager.widget.MediaListItemViewHolder;
import com.edgar.filemanager.widget.RecyclerCursorAdapter;

import androidx.annotation.CallSuper;

/**
 * 多媒体文件列表适配器
 */
public abstract class MediaCursorAdapter extends RecyclerCursorAdapter<MediaCursorItemViewHolder> {

    protected LayoutInflater mInflater;
    private MediaCursorItemViewHolder.OnMediaItemClickListener mMediaItemClickListener = new MediaCursorItemViewHolder.OnMediaItemClickListener() {
        @Override
        public void onMediaItemClick(int position) {
            final Cursor cursor = getCursor();
            cursor.moveToPosition(position);
            MediaCursorAdapter.this.onMediaItemClick(cursor, position);
        }
    };

    public MediaCursorAdapter(Context context) {
        super(context, null, false);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MediaCursorItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MediaCursorItemViewHolder holder = onCreateMediaViewHolder(mInflater, parent, viewType);
        holder.setPlaceholderDrawable(getPlaceholderDrawable());
        holder.setOnMediaItemClickListener(mMediaItemClickListener);
        return holder;
    }

    protected MediaCursorItemViewHolder onCreateMediaViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new MediaListItemViewHolder(mInflater, parent);
    }

    @Override
    @CallSuper
    public void onBindCursorViewHolder(MediaCursorItemViewHolder holder, Cursor cursor, int position) {
        holder.setMediaImageUri(getMediaImageUri(cursor));
        holder.displayCursor(cursor);
    }

    protected int getPlaceholderDrawable() {
        return 0;
    }

    protected Uri getMediaImageUri(Cursor cursor) {
        return null;
    }

    protected void onMediaItemClick(Cursor cursor, int position) {}
}