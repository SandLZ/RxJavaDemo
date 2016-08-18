package me.sandlz.rxjavademo.modules.login.model;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 * Usage :
 */
public interface LoginApi {
    // 可以全拼
    @POST("login/userLogin")
    Observable<List<UserEntity>>
        doLogin(@Body HashMap<String, Object> body);
}
