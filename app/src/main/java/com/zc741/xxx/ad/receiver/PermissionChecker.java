package com.zc741.xxx.ad.receiver;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * Created by jiae on 2016/5/16.
 */
public class PermissionChecker {

    private final Context mContext;

    public PermissionChecker(Context context) {
        mContext = context.getApplicationContext();
    }

    //判断权限集合
    public boolean lackPermission(String... permissions) {
        for (String permission : permissions) {
            if (lackPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    //判断是否缺少权限
    private boolean lackPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED;
    }
}
