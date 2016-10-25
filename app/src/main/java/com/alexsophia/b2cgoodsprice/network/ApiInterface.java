package com.alexsophia.b2cgoodsprice.network;

import com.alexsophia.b2cgoodsprice.features.main.entity.MovieEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * ApiInterface
 * <p>
 * Created by Alexander on 2016/10/25.
 */
public interface ApiInterface {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
