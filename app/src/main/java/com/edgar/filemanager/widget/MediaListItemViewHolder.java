package com.edgar.filemanager.widget;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edgar.filemanager.R;
import com.edgar.filemanager.utils.FormatUtils;
import com.edgar.filemanager.utils.MediaUtils;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edgar on 2018/10/29.
 */
public class MediaListItemViewHolder extends MediaCursorItemViewHolder {

    private SimpleDraweeView mFileTypeImgView;
    private TextView mDisplayNameView;
    private TextView mFileSizeView;
    private TextView mLastModifiedTimeView;

    public MediaListItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent, R.layout.file_list_item);
        mFileTypeImgView = itemView.findViewById(R.id.file_img);
        mDisplayNameView = itemView.findViewById(R.id.file_name);
        mFileSizeView = itemView.findViewById(R.id.file_size);
        mLastModifiedTimeView = itemView.findViewById(R.id.file_modify_time);
    }

    @Override
    public void displayCursor(Cursor cursor) {
        mDisplayNameView.setText(MediaUtils.getDisplayName(cursor));
        mFileSizeView.setText(FormatUtils.formatFileSize(mFileSizeView.getContext(),MediaUtils.getSize(cursor)));
        mLastModifiedTimeView.setText(FormatUtils.formatTime(MediaUtils.getLastModified(cursor)));
        if (mPlaceholderDrawable != 0) {
            mFileTypeImgView.getHierarchy().setPlaceholderImage(mPlaceholderDrawable);
        }
        mFileTypeImgView.setImageURI(mMediaImageUri);
    }
}