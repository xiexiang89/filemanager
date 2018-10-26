package com.edgar.filemanager;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edgar.filemanager.utils.FormatUtils;
import com.edgar.filemanager.utils.MediaUtils;
import com.edgar.filemanager.utils.ViewUtils;

import androidx.cursoradapter.widget.CursorAdapter;

/**
 * Created by Edgar on 2018/10/26.
 */
public class MediaCursorAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public MediaCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        init(context);
    }

    public MediaCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        init(context);
    }

    private void init(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.file_list_item, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewUtils.setText(view, R.id.file_name, MediaUtils.getDisplayName(cursor));
        ViewUtils.setText(view, R.id.file_size, FormatUtils.formatFileSize(view.getContext(),MediaUtils.getSize(cursor)));
        ViewUtils.setText(view, R.id.file_modify_time, FormatUtils.formatTime(MediaUtils.getLastModified(cursor)));
    }
}
