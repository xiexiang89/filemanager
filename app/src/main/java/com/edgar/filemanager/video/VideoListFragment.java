package com.edgar.filemanager.video;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.edgar.filemanager.MediaCursorFragment;
import com.edgar.filemanager.R;
import com.edgar.filemanager.constant.LoaderConstant;
import com.edgar.filemanager.widget.GridAutoFitLayoutManager;
import com.edgar.filemanager.widget.GridSpacingItemDecoration;

import androidx.recyclerview.widget.RecyclerView;

public class VideoListFragment extends MediaCursorFragment<VideoListAdapter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSortOrder(MediaStore.Video.DEFAULT_SORT_ORDER);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getRecyclerView().addItemDecoration(new GridSpacingItemDecoration(getResources().getDimensionPixelOffset(R.dimen.grid_space),true));
    }

    @Override
    protected RecyclerView.LayoutManager onCreateLayoutManager() {
        return new GridAutoFitLayoutManager(getContext(),getResources().getDimensionPixelOffset(R.dimen.video_image_size));
    }

    @Override
    protected VideoListAdapter onCreateMediaCursorAdapter() {
        return new VideoListAdapter(getContext());
    }

    @Override
    protected int getLoaderId() {
        return LoaderConstant.VIDEO_LOADER_ID;
    }

    @Override
    protected Uri getContentUri() {
        return MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    }
}