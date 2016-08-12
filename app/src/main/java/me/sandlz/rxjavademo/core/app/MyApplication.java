package me.sandlz.rxjavademo.core.app;

import android.app.Application;

import me.sandlz.rxjavademo.utils.LogUtil;

/**
 * Created by liuzhu on 16/8/12.
 * Description :
 * Usage :
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        // 配置Logger
        LogUtil.init();

    }

    public static MyApplication getIntstance() {
        return mApplication;
    }
}
