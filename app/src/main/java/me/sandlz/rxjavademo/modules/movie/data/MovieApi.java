package me.sandlz.rxjavademo.modules.movie.data;

import java.util.List;

import me.sandlz.rxjavademo.core.net.BaseHttpResult;
import me.sandlz.rxjavademo.modules.movie.model.MovieEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liuzhu on 16/7/31.
 * Description :
 * Usage :
 */
public interface MovieApi {
    // 可以全拼
    @GET("top250")
    Observable<BaseHttpResult<List<MovieEntity>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
