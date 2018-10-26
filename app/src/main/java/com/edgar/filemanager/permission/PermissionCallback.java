package com.edgar.filemanager.permission;

/**
 * Created by Edgar on 2018/10/26.
 */
public abstract class PermissionCallback {

    public void onGranted(String permission) {}
    public void onDenied(String permission) {}
    public void onPermissionResult(String[] permissions, String[] deniedPermissions, int deniedPermissionCount) {}
}
