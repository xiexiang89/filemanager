package com.edgar.filemanager;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.edgar.filemanager.file.FileListFragment;
import com.edgar.filemanager.music.MusicListFragment;
import com.edgar.filemanager.permission.PermissionCallback;
import com.edgar.filemanager.permission.PermissionRequest;
import com.edgar.filemanager.video.VideoListFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends FileBaseActivity {

    private static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabPagerAdapter mTabPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionRequest.build(this).request(0, PERMISSIONS, new PermissionCallback(){
            @Override
            public void onPermissionResult(String[] permissions, String[] deniedPermissions, int deniedPermissionCount) {
                super.onPermissionResult(permissions, deniedPermissions, deniedPermissionCount);
                if (deniedPermissionCount > 0) {
                    finish();
                    return;
                }
                setupView();
            }
        });
    }

    private void setupView() {
        setupToolBar();
        setupViewPager();
        setupTabLayout();
        setupDrawerLayout();
    }

    private void setupToolBar() {
        mToolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.ic_menu_white);
        setTitle("");
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });
    }

    private void setupTabLayout() {
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(ContextCompat.getColor(this,R.color.tab_normal_color),
                ContextCompat.getColor(this,R.color.tab_select_color));
        mTabLayout.setTabIndicatorFullWidth(false);
        mTabLayout.setupWithViewPager(mViewPager,false);
    }

    private void setupDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
    }

    private void setupViewPager() {
        mViewPager = findViewById(R.id.view_pager);
        mTabPagerAdapter = new TabPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(mTabPagerAdapter.getCount()-1);
        mViewPager.setAdapter(mTabPagerAdapter);
    }

    private class TabPagerAdapter extends FragmentPagerAdapter {

        private WeakReference<Context> mContextRef;
        private List<String[]> mTabs;

        private TabPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContextRef = new WeakReference<>(context);
            mTabs = new ArrayList<>();
            mTabs.add(new String[]{context.getString(R.string.phone), FileListFragment.class.getCanonicalName()});
            mTabs.add(new String[]{context.getString(R.string.music), MusicListFragment.class.getCanonicalName()});
            mTabs.add(new String[]{context.getString(R.string.video), VideoListFragment.class.getCanonicalName()});
            mTabs.add(new String[]{context.getString(R.string.image), FileListFragment.class.getCanonicalName()});
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(mContextRef.get(), mTabs.get(position)[1]);
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position)[0];
        }
    }
}