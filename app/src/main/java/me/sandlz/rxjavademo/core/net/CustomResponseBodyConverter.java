package me.sandlz.rxjavademo.core.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.sandlz.rxjavademo.utils.LogUtil;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by liuzhu on 16/8/14.
 * Description : 自定义响应Converter
 * Usage :
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson gson;
    CustomResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.adapter = adapter;
        this.gson = gson;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        // 自定义解析规则
        JSONObject jsonObject;
        try {
            LogUtil.d(value);
            jsonObject = new JSONObject(value.string());
            JSONObject e1 = jsonObject.getJSONObject("result");
            int error2 = e1.getInt("errorCode");
            if(error2 == 0) {
                JSONObject jsonObject2 = e1.getJSONObject("content");
                JSONArray jsonArray = jsonObject2.getJSONArray("list");
                return (T) jsonArray;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
