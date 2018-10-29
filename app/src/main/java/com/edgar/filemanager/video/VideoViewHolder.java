package com.edgar.filemanager.video;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edgar.filemanager.R;
import com.edgar.filemanager.utils.FormatUtils;
import com.edgar.filemanager.utils.MediaUtils;
import com.edgar.filemanager.widget.MediaCursorItemViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Edgar on 2018/10/29.
 */
public class VideoViewHolder extends MediaCursorItemViewHolder {

    private SimpleDraweeView mVideoImgView;
    private TextView mFileSizeView;
    private TextView mVideoNameView;

    public VideoViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent, R.layout.video_list_item);
        mVideoImgView = itemView.findViewById(R.id.video_img);
        mFileSizeView = itemView.findViewById(R.id.file_size);
        mVideoNameView = itemView.findViewById(R.id.video_name);
    }

    @Override
    public void displayCursor(Cursor cursor) {
        super.displayCursor(cursor);
        mVideoImgView.setImageURI(mMediaImageUri);
        mFileSizeView.setText(FormatUtils.formatFileSize(mFileSizeView.getContext(), MediaUtils.getSize(cursor)));
        mVideoNameView.setText(MediaUtils.getTitle(cursor));
    }
}