package com.yash.movie_app.model

import java.io.Serializable

/**
 * Created by Joshi on 01-06-2020.
 */
class Results : Serializable {

    var id: Int? = null
    var title: String? = null
    var poster_path: String? = null
    var runtime: Int? = null
    var release_date: String? = null
    var vote_average: Double? = null    //rating
    var vote_count: Int? = null    //votes
    var overview: String? = null    //votes

    var genres: List<MovieDetails>? = null
}