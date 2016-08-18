package me.sandlz.rxjavademo.core.view;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import me.sandlz.rxjavademo.R;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by liuzhu on 16/8/13.
 * Description :
 *  1. 绑定页面
 * Usage :
 */
public class BaseActivity extends AppCompatActivity {
    public Activity mActivity;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        mActivity = this;
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        mActivity = this;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        mActivity = this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    @Override
    protected void onDestroy() {
        onUnsubscribe();
        super.onDestroy();
    }

    private CompositeSubscription mCompositeSubscription;

    public void onUnsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//取消注册，以避免内存泄露
        }
    }

    public void addSubscription(Subscription subscription) {
//        if (mCompositeSubscription == null) {
        mCompositeSubscription = new CompositeSubscription();
//        }
        mCompositeSubscription.add(subscription);
    }

    /**
     * toolBar setting
     * @param title
     * @return
     */
    public Toolbar initToolBar(String title) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return toolbar;
    }

    public void initToolBar(String title, int titleColor, boolean showBack) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        Resources resource = (Resources) getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(titleColor);
        if (csl != null) {
            toolbar_title.setTextColor(csl);
        }
        if (showBack) {
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public Toolbar initToolBar(String title, boolean showBack) {
        Toolbar toolbar = (Toolbar) mActivity.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(showBack);
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void toastShow(int resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String resId) {
        Toast.makeText(mActivity, resId, Toast.LENGTH_SHORT).show();
    }


}
