package com.yash.movie_app.remote

import com.yash.movie_app.model.Movies
import com.yash.movie_app.model.Results
import io.reactivex.Single
import org.json.JSONObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Joshi on 01-06-2020.
 */

interface ApiService {

    companion object {
        val baseUrl: String get() = "https://api.themoviedb.org/3/"
        val fetchImageUrl: String get() = "https://image.tmdb.org/t/p/w500"
    }

    @GET("movie/now_playing")
    fun getNowPlaying(): Single<Movies>

    @GET("movie/popular")
    fun getPopular(): Single<Movies>

    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id") id: Int): Single<Results>

    @GET("movie/{movie_id}/credits")
    fun getCast(@Path("movie_id") id: Int): Single<Movies>
}