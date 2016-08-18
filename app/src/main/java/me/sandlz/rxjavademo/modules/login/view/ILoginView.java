package me.sandlz.rxjavademo.modules.login.view;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
public interface ILoginView {

    void loginButtonState(boolean isChange);

    void callBackLoginResult(boolean isSuccess, String message);

    void loadingViewState(boolean isShow);

}
