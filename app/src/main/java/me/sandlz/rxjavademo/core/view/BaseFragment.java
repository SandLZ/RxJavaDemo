package me.sandlz.rxjavademo.core.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by liuzhu on 16/8/13.
 * Description :
 * Usage :
 */
public class BaseFragment extends Fragment {
    public Activity mActivity;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mActivity = getActivity();
    }

//    public Toolbar initToolBar(View view, String title) {
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
//        toolbar_title.setText(title);
//        return toolbar;
//    }


    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
//        }
        mCompositeSubscription.add(subscription);
    }
}
