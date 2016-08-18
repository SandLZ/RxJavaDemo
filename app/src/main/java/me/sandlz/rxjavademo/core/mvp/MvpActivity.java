package me.sandlz.rxjavademo.core.mvp;

import android.os.Bundle;

import me.sandlz.rxjavademo.core.view.BaseActivity;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
