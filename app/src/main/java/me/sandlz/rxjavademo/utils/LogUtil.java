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
    public static void i(String msg) {
        if (isDebug)
            Logger.i(msg);
    }
    public static void i(@NonNull String tag, @NonNull String msg) {
        if (isDebug)
            Logger.log(Logger.INFO,tag,msg,(Throwable)null);
    }

    // debug
    public static void d(String msg) {
        if (isDebug)
            Logger.d(msg);
    }
    public static void d(Object msg) {
        if (isDebug)
            Logger.d(msg);
    }

    public static void d(@NonNull String tag, @NonNull String msg) {
        if (isDebug)
            Logger.log(Logger.DEBUG,tag,msg,(Throwable)null);
    }

    // error
    public static void e(String msg) {
        if (isDebug)
            Logger.e(msg);
    }

    public static void e(@NonNull String tag, @NonNull String msg) {
        if (isDebug)
            Logger.log(Logger.ERROR,tag,msg,(Throwable)null);
    }

    // verbose
    public static void v(String msg) {
        if (isDebug)
            Logger.v(msg);
    }

    public static void v(@NonNull String tag, @NonNull String msg) {
        if (isDebug)
            Logger.log(Logger.VERBOSE,tag,msg,(Throwable)null);
    }

    // warning
    public static void w(String msg) {
        if (isDebug)
            Logger.w(msg);
    }

    public static void w(@NonNull String tag, @NonNull String msg) {
        if (isDebug)
            Logger.log(Logger.WARN,tag,msg,(Throwable)null);
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
