package me.sandlz.rxjavademo.core.net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by liuzhu on 16/8/14.
 * Description : 自定义请求Converter
 * Usage :
 */
public class CustomRequestBodyConverter <T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;
    CustomRequestBodyConverter(Gson gson,TypeAdapter<T> adapter){
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(),UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter,value);
        jsonWriter.flush();
        return RequestBody.create(MEDIA_TYPE,buffer.readByteString());
    }
}
