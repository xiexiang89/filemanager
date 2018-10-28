package com.edgar.filemanager.widget;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edgar.filemanager.R;
import com.edgar.filemanager.utils.FormatUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;

public class FileItemViewHolder extends RecyclerView.ViewHolder {

    private SimpleDraweeView mFileTypeImgView;
    private TextView mDisplayNameView;
    private TextView mFileSizeView;
    private TextView mLastModifiedTimeView;

    public FileItemViewHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.file_list_item,parent,false));
        mFileTypeImgView = itemView.findViewById(R.id.file_img);
        mDisplayNameView = itemView.findViewById(R.id.file_name);
        mFileSizeView = itemView.findViewById(R.id.file_size);
        mLastModifiedTimeView = itemView.findViewById(R.id.file_modify_time);
    }

    public void setFileTypeImage(Uri uri, @DrawableRes int placeholderImage) {
        mFileTypeImgView.getHierarchy().setPlaceholderImage(placeholderImage);
        mFileTypeImgView.setImageURI(uri);
    }

    public void setDisplayName(String displayName) {
        mDisplayNameView.setText(displayName);
    }

    public void setFileSize(long size) {
        mFileSizeView.setText(FormatUtils.formatFileSize(mFileSizeView.getContext(), size));
    }

    public void setFileLastModifyTime(long time) {
        mLastModifiedTimeView.setText(FormatUtils.formatTime(time));
    }
}