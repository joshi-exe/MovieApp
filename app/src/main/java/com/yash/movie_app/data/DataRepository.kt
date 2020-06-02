package com.yash.movie_app.data

import com.yash.movie_app.remote.RemoteDataSource
import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
class DataRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    fun getNowPlaying() = remoteDataSource.getNowPlaying()
    fun getPopular() = remoteDataSource.getPopular()
    fun getDetails(id: Int) = remoteDataSource.getDetails(id)
    fun getCast(id: Int) = remoteDataSource.getCast(id)
}