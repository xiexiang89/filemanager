package com.edgar.filemanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.ListFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by Edgar on 2018/10/26.
 */
public class FileListBaseFragment extends ListFragment implements SwipeRefreshLayout.OnRefreshListener {

    private boolean mEnableRefresh = true;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (mEnableRefresh) {
            view = wrapRefreshLayout(view);
        }
        return view;
    }

    private View wrapRefreshLayout(View childView) {
        mRefreshLayout = new SwipeRefreshLayout(getContext());
        mRefreshLayout.setId(R.id.refresh);
        mRefreshLayout.addView(childView);
        mRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mRefreshLayout.setOnRefreshListener(this);
        return mRefreshLayout;
    }

    public void setEnableRefresh(boolean enableRefresh) {
        mEnableRefresh = enableRefresh;
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    public void onRefresh() {
    }
}