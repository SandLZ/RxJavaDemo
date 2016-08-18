package me.sandlz.rxjavademo.core.mvp;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
