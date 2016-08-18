package me.sandlz.rxjavademo.core.net;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.sandlz.rxjavademo.BuildConfig;
import me.sandlz.rxjavademo.core.app.MyApplication;
import me.sandlz.rxjavademo.utils.DeviceUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by liuzhu on 16/8/14.
 * Description :
 *  Retrofit 网络请求管理类
 *  1. 请求时,获取retrofit实例 -> 请求
 *  2. initOkHttp() 配置okhttp请求 包括请求头等
 *  3. 拦截器：自定义拦截器满足不同需求 CustomConverterFactory
 *  注意：BASE_URL: 请求的URL前缀 (如变化则须要重新创建retrofit实例)
 *
 * Usage :
 */
public class RetrofitManager {
    private static OkHttpClient okHttpClient = null;
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://dev.handsmap.cn:6880/sims_lushan/rest/v1/";
    public static RetrofitManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    private void init() {
        initOkHttp();
        initRetrofit();
    }

    private RetrofitManager() {
        init();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


    private static void initOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();
                Map<String, Object> p = new HashMap<String, Object>();
                p.put("device","2");
                p.put("deviceId", DeviceUtil.getUniqueId(MyApplication.getIntstance()));
                p.put("deviceVersion",DeviceUtil.getAndroidVersion());
                JSONObject object = new JSONObject(p);
                String postBodyString = object.toString();
                String o = bodyToString(request.body());
                // 判断请求参数是否为"{}"
                if (null != o && o.startsWith("{")) {
                    if (o.length() == 2) {
                        // do nothing
                    }else {
                        if (null != postBodyString && postBodyString.endsWith("}")) {
                            postBodyString = postBodyString.substring(0,postBodyString.length() - 1);
                            postBodyString += ",";
                        }
                        o = o.substring(1,o.length());
                        postBodyString += o;
                    }
                }
                request = requestBuilder
                        .post(RequestBody.create(MediaType.parse("application/json"), postBodyString))
                        .build();
                return chain.proceed(request);
            }
        });

        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build();
    }

    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(CustomConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
