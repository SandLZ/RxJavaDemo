package me.sandlz.rxjavademo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by liuzhu on 16/8/15.
 * Description :
 * Usage :
 */
public class DeviceUtil {
    public DeviceUtil() {
    }

    public static String getUniqueId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return androidId;
    }

    public static int getAppVersion(Context context) {
        return getPackageInfo(context).versionCode;
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager e = context.getPackageManager();
            pi = e.getPackageInfo(context.getPackageName(), 0);
            return pi;
        } catch (Exception var3) {
            var3.printStackTrace();
            return pi;
        }
    }

}
