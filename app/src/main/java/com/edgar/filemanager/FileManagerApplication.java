package com.edgar.filemanager;

import android.app.Application;

import com.edgar.filemanager.utils.FrescoInit;

/**
 * Created by Edgar on 2018/10/27.
 */
public class FileManagerApplication extends Application {

    private static FileManagerApplication sApplication;

    public static FileManagerApplication get() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        FrescoInit.init(getApplicationContext());
    }
}
