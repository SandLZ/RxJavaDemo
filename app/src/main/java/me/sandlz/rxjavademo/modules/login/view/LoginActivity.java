package me.sandlz.rxjavademo.modules.login.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import butterknife.Bind;
import butterknife.OnClick;
import me.sandlz.rxjavademo.R;
import me.sandlz.rxjavademo.core.mvp.MvpActivity;
import me.sandlz.rxjavademo.modules.Starter.view.StarterActivity;
import me.sandlz.rxjavademo.modules.login.presenter.LoginPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author zliu
 * @Date 16/8/12 14:23
 * @Describe 登录页
 *
 */
public class LoginActivity extends MvpActivity<LoginPresenter> implements ILoginView{
    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.bt_go)
    Button btGo;
    @Bind(R.id.cv)
    CardView cv;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.login_mProgressBar)
    CircleProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        mProgressBar.setShowArrow(true);
        initToolBar("登录",false);
        //
        Observable<CharSequence> observable = RxTextView.textChanges(etUsername);
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        if (charSequence.toString().length() > 0 && etPassword.getText().toString().length() > 0) {
                            btGo.setTextColor(getResources().getColor(R.color.colorPrimary));
                        }else {
                            btGo.setTextColor(0xFFd3d3d3);
                        }
                    }
                });
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void loginButtonState(boolean isWork) {
        // 根据是否有值控制
    }

    @Override
    public void callBackLoginResult(boolean isSuccess, String message) {
        Intent index = new Intent();
        index.setClass(this, StarterActivity.class);
        startActivity(index);
    }

    @Override
    public void loadingViewState(boolean isShow) {
        mProgressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }



    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, RegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                doLogin();
                break;
        }
    }

    private void doLogin() {
        String userName = etUsername.getText().toString();
        String userPwd = etPassword.getText().toString();
        mvpPresenter.doLogin(userName,userPwd);
    }

}
