package com.edgar.filemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Edgar on 2018/10/26.
 */
public class RefreshListFragment extends FileListBaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnableRefresh(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getContext();
        final RecyclerView recyclerView = getRecyclerView();
        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(context,R.drawable.list_inset_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
