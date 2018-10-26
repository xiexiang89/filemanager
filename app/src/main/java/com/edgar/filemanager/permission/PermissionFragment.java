package com.edgar.filemanager.permission;

import android.content.pm.PackageManager;

import com.edgar.filemanager.utils.ArrayUtils;

import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;

/**
 * Created by Edgar on 2018/10/26.
 */
public class PermissionFragment extends Fragment {

    private SparseArrayCompat<PermissionCallback> mPermissionCallbackArray;

    void addPermissionCallback(int requestCode, PermissionCallback callback) {
        if (mPermissionCallbackArray == null) {
            mPermissionCallbackArray = new SparseArrayCompat<>();
        }
        mPermissionCallbackArray.put(requestCode, callback);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ArrayUtils.isNotEmpty(permissions) && ArrayUtils.isNotEmpty(grantResults)) {
            final PermissionCallback callback = mPermissionCallbackArray.get(requestCode);
            if (callback != null) {
                mPermissionCallbackArray.remove(requestCode);
                String[] deniedPermissions = null;
                for (int i=0, length=permissions.length; i < length; i++) {
                    final String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        callback.onGranted(permission);
                    } else {
                        deniedPermissions = ArrayUtils.appendElement(String.class,deniedPermissions,permission);
                        callback.onDenied(permission);
                    }
                }
                callback.onPermissionResult(permissions, deniedPermissions, ArrayUtils.length(deniedPermissions));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPermissionCallbackArray != null) {
            mPermissionCallbackArray.clear();
        }
    }
}