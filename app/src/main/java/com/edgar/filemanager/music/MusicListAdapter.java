package com.edgar.filemanager.music;

import android.content.Context;
import android.database.Cursor;
import android.view.ViewGroup;

import com.edgar.filemanager.MediaFileListAdapter;
import com.edgar.filemanager.R;
import com.edgar.filemanager.utils.MediaUtils;
import com.edgar.filemanager.widget.FileItemViewHolder;

public class MusicListAdapter extends MediaFileListAdapter<FileItemViewHolder> {

    public MusicListAdapter(Context context) {
        super(context);
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
        holder.setFileTypeImage(MediaUtils.getAudioAlbumImageUri(cursor), R.drawable.ic_music_placeholder);
    }
}