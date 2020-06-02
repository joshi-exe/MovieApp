package com.yash.movie_app.remote

import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
open class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getNowPlaying() = apiService.getNowPlaying()
    fun getPopular() = apiService.getPopular()
    fun getDetails(id: Int) = apiService.getDetails(id)
    fun getCast(id: Int) = apiService.getCast(id)
}