package com.yash.movie_app.domain

/**
 * Created by Joshi on 01-06-2020.
 */
abstract class UseCase<in P, R> {

    @Throws(RuntimeException::class)
    abstract fun execute(parameters: P): R

}