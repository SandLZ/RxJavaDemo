package me.sandlz.rxjavademo.core.mvp;

import android.os.Bundle;
import android.view.View;

import me.sandlz.rxjavademo.core.view.BaseFragment;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
