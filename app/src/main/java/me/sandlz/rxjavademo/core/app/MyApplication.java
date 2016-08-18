package me.sandlz.rxjavademo.core.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import me.sandlz.rxjavademo.core.db.DBHelper;
import me.sandlz.rxjavademo.core.exception.LocalCrashFileHandler;
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

        // 数据库
        DBHelper.init(this);

        //配置程序异常退出处理
        Thread.setDefaultUncaughtExceptionHandler(new LocalCrashFileHandler(this));

        // 内存泄漏检测
        LeakCanary.install(this);
    }

    public static MyApplication getIntstance() {
        return mApplication;
    }
}
