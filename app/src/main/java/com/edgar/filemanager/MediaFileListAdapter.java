package com.edgar.filemanager;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.edgar.filemanager.utils.MediaUtils;
import com.edgar.filemanager.widget.FileItemViewHolder;
import com.edgar.filemanager.widget.RecyclerCursorAdapter;

import androidx.annotation.DrawableRes;

/**
 * 多媒体文件列表适配器
 */
public abstract class MediaFileListAdapter extends RecyclerCursorAdapter<FileItemViewHolder> {

    private LayoutInflater mInflater;

    public MediaFileListAdapter(Context context) {
        super(context, null, false);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FileItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileItemViewHolder(mInflater, parent);
    }

    @Override
    public void onBindCursorViewHolder(FileItemViewHolder holder, Cursor cursor, int position) {
        holder.setDisplayName(MediaUtils.getDisplayName(cursor));
        holder.setFileSize(MediaUtils.getSize(cursor));
        holder.setFileLastModifyTime(MediaUtils.getLastModified(cursor));
        holder.setFileTypeImage(getImageUri(cursor), getMediaPlaceholderDrawable());
    }

    protected abstract @DrawableRes int getMediaPlaceholderDrawable();

    protected Uri getImageUri(Cursor cursor) {
        return null;
    }
}