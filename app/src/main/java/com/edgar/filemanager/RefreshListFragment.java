package com.edgar.filemanager;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.core.content.ContextCompat;

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
        final ListView listView = getListView();
        listView.setDivider(ContextCompat.getDrawable(getContext(),R.drawable.list_inset_divider));
        listView.setDividerHeight(getResources().getDimensionPixelOffset(R.dimen.list_divider_height));
    }
}
