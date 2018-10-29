package com.edgar.filemanager;

import android.app.Application;
import android.content.ContentResolver;

import com.edgar.filemanager.fresco.FrescoInit;

/**
 * Created by Edgar on 2018/10/27.
 */
public class FileManagerApplication extends Application {

    private static FileManagerApplication sApplication;

    public static FileManagerApplication get() {
        return sApplication;
    }

    public static ContentResolver contentResolver() {
        return sApplication.getContentResolver();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        FrescoInit.init(getApplicationContext());
    }
}
