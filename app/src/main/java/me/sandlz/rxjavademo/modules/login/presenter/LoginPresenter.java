package me.sandlz.rxjavademo.modules.login.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import me.sandlz.rxjavademo.core.mvp.BasePresenter;
import me.sandlz.rxjavademo.core.net.RetrofitManager;
import me.sandlz.rxjavademo.modules.login.model.LoginApi;
import me.sandlz.rxjavademo.modules.login.model.UserEntity;
import me.sandlz.rxjavademo.modules.login.view.ILoginView;
import me.sandlz.rxjavademo.utils.LogUtil;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView iLoginView) {
        attachView(iLoginView);
    }

    public void doLogin(String name, String pwd) {
        // do login action
        mvpView.loadingViewState(true);
        HashMap<String, Object> prams = new HashMap<>();
        prams.put("pushID", "448bf76a9f8401c7");
        prams.put("pwd", pwd);
        prams.put("username", name);
        Observable observable = getLoginApi().doLogin(prams);
        addSubscription(observable, new Subscriber<JSONArray>(){

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mvpView.loadingViewState(false);
                mvpView.callBackLoginResult(false,e.toString());
                LogUtil.d("onError");
            }

            @Override
            public void onNext(JSONArray jsonArray) {
                List<UserEntity> userEntities = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<UserEntity>>() {
                }.getType());
                mvpView.loadingViewState(false);
                mvpView.callBackLoginResult(true,"");
                LogUtil.d("onNext");
            }
        });
    }

    public LoginApi getLoginApi() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        return retrofit.create(LoginApi.class);
    }

}
