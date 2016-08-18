package me.sandlz.rxjavademo.modules.Starter.presenter;

import java.util.ArrayList;
import java.util.List;

import me.sandlz.rxjavademo.core.mvp.BasePresenter;
import me.sandlz.rxjavademo.modules.Starter.view.Interface.IBasicView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by liuzhu on 16/8/18.
 * Description :
 * Usage :
 */
public class BasicPresenter extends BasePresenter<IBasicView> {

    public BasicPresenter(IBasicView iBasicView) {
        attachView(iBasicView);
    }

    /**
     * 基本原理
     */
    public void getData1() {
        // 利用Observable 发出事件
        Observable<List<String>> observable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < 4; i++) {
                    list.add("hello world "+i);
                }
                subscriber.onNext(list);
            }
        });
        // Subscriber 处理事件
        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> s) {
                mvpView.callBackGetDataResult(s);
            }
        };
        // 订阅
        observable.subscribe(subscriber);
    }

    /**
     * 简化
     */
    public void getData2() {
        List<String> list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            list.add("hello world "+i);
        }
        Observable.just(list)
                .subscribeOn(Schedulers.computation())// 计算线程
                .observeOn(AndroidSchedulers.mainThread())// 回调主线程
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> s) {
                        mvpView.callBackGetDataResult(s);
                    }
                });

    }


}
