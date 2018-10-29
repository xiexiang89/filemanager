package com.edgar.filemanager.widget;

import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Edgar on 2018/10/29.
 */
public class MediaCursorItemViewHolder extends RecyclerView.ViewHolder {

    protected int mPlaceholderDrawable;
    protected Uri mMediaImageUri;
    private OnMediaItemClickListener mOnMediaItemClickListener;
    private View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            callMediaItemClick();
        }
    };

    public MediaCursorItemViewHolder(LayoutInflater inflater, ViewGroup parent, @LayoutRes int layoutId) {
        this(inflater.inflate(layoutId, parent,false));
        itemView.setOnClickListener(mItemClickListener);
    }

    public MediaCursorItemViewHolder(View itemView) {
        super(itemView);
    }

    public void setPlaceholderDrawable(int placeholder) {
        mPlaceholderDrawable = placeholder;
    }

    public void setMediaImageUri(Uri uri) {
        mMediaImageUri = uri;
    }

    public void displayCursor(Cursor cursor) {}

    public void setOnMediaItemClickListener(OnMediaItemClickListener listener) {
        mOnMediaItemClickListener = listener;
    }

    protected void callMediaItemClick() {
        if (mOnMediaItemClickListener != null) {
            mOnMediaItemClickListener.onMediaItemClick(getAdapterPosition());
        }
    }

    public interface OnMediaItemClickListener {
        void onMediaItemClick(int position);
    }
}