package com.edgar.filemanager;

import android.content.Context;
import android.view.LayoutInflater;
import com.edgar.filemanager.widget.RecyclerCursorAdapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 多媒体文件列表适配器
 */
public abstract class MediaFileListAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerCursorAdapter<VH> {

    protected LayoutInflater mInflater;

    public MediaFileListAdapter(Context context) {
        super(context, null, false);
        mInflater = LayoutInflater.from(context);
    }
}