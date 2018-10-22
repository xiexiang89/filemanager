package com.edgar.filemanager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends FileBaseActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);
        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setSelectedTabIndicatorHeight(0);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(ContextCompat.getColor(this,R.color.tab_normal_color),
                ContextCompat.getColor(this,R.color.tab_select_color));
        mTabLayout.addTab(mTabLayout.newTab().setText("分类"));
        mTabLayout.addTab(mTabLayout.newTab().setText("手机"));
    }
}
