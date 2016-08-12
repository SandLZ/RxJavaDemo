package me.sandlz.rxjavademo.utils;

import android.support.annotation.NonNull;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by liuzhu on 16/8/12.
 * Description : 日志工具类
 * Usage :
 */
public class LogUtil {
    public static boolean isDebug = true;
    private static final String TAG = "LogUtil";

    public static void init() {
        Logger
                .init(TAG)                      // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // default LogLevel.FULL
                .methodOffset(2);               // default 0
    }

    /**
     * 常规Log
     *
     * @param msg
     */

    // info
    public static void i(Object msg) {
        if (isDebug)
            Logger.i(TAG, msg);
    }

    public static void i(@NonNull String tag, Object msg) {
        if (isDebug)
            Logger.i(tag, msg);
    }

    // debug
    public static void d(String msg) {
        if (isDebug)
            Logger.d(TAG, msg);
    }

    public static void d(@NonNull String tag, Object msg) {
        if (isDebug)
            Logger.d(tag, msg);
    }

    // error
    public static void e(Object msg) {
        if (isDebug)
            Logger.e(TAG, msg);
    }

    public static void e(@NonNull String tag, Object msg) {
        if (isDebug)
            Logger.e(tag, msg);
    }

    // verbose
    public static void v(Object msg) {
        if (isDebug)
            Logger.v(TAG, msg);
    }

    public static void v(@NonNull String tag, Object msg) {
        if (isDebug)
            Logger.v(tag, msg);
    }

    // warning
    public static void w(String msg) {
        if (isDebug)
            Logger.w(TAG, msg);
    }

    public static void w(@NonNull String tag, Object msg) {
        if (isDebug)
            Logger.w(tag, msg);
    }

    public static void json(String json) {
        if (isDebug)
            Logger.json(json);
    }

    public static void xml(String json) {
        if (isDebug)
            Logger.xml(json);
    }


}
