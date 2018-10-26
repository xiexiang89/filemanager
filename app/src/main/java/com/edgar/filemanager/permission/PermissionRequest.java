package com.edgar.filemanager.permission;

import android.content.pm.PackageManager;
import android.os.Build;

import com.edgar.filemanager.utils.ArrayUtils;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by Edgar on 2018/10/26.
 */
public class PermissionRequest {

    private PermissionFragment mPermissionFragment;

    private static String makeFragmentTag() {
        return "Fragment#PermissionRequest";
    }

    private static PermissionFragment getPermissionFragment(FragmentManager fragmentManager, final String tag) {
        PermissionFragment fragment = (PermissionFragment) fragmentManager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = new PermissionFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(fragment, tag);
            transaction.commitNowAllowingStateLoss();
        }
        return fragment;
    }

    private PermissionRequest(FragmentManager fragmentManager) {
        mPermissionFragment = getPermissionFragment(fragmentManager,makeFragmentTag());
    }

    private PermissionRequest(FragmentActivity activity) {
        this(activity.getSupportFragmentManager());
    }

    public static PermissionRequest build(Fragment fragment) {
        return new PermissionRequest(fragment.getActivity());
    }

    public static PermissionRequest build(FragmentActivity activity) {
        return new PermissionRequest(activity);
    }

    public void request(int requestCode, String[] permissions, PermissionCallback callback) {
        if (ArrayUtils.isEmpty(permissions) || callback == null) {
            return;
        }
        String[] requestPermissions = null;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mPermissionFragment.getContext(), permission) == PackageManager.PERMISSION_GRANTED) {
                callback.onGranted(permission);
            } else {
                requestPermissions = ArrayUtils.appendElement(String.class, requestPermissions, permission);
            }
        }
        if (ArrayUtils.isNotEmpty(requestPermissions)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mPermissionFragment.addPermissionCallback(requestCode, callback);
                mPermissionFragment.requestPermissions(requestPermissions, requestCode);
            } else {
                for (String permission : requestPermissions) {
                    callback.onDenied(permission);
                }
                callback.onPermissionResult(permissions,requestPermissions, requestPermissions.length);
            }
        } else {
            callback.onPermissionResult(permissions, null, 0);
        }
    }
}