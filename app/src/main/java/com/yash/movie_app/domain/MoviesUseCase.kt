package com.yash.movie_app.domain

import com.yash.movie_app.data.DataRepository
import com.yash.movie_app.model.Movies
import io.reactivex.Single
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
class MoviesUseCase @Inject constructor(private val dataRepository: DataRepository) :
    UseCase<Unit, Single<Movies>>() {

    //for now playing
    override fun execute(parameters: Unit) = dataRepository.getNowPlaying()

    //for popular
    fun getPopular() = dataRepository.getPopular()

    //for details
    fun getDetails(id: Int) = dataRepository.getDetails(id)

    //for cast
    fun getCast(id: Int) = dataRepository.getCast(id)
}