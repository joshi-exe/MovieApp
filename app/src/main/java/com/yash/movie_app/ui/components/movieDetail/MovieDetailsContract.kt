package com.yash.movie_app.ui.components.movieDetail

import com.yash.movie_app.model.MovieDetails
import com.yash.movie_app.ui.base.BaseContract

/**
 * Created by Joshi on 01-06-2020.
 */
interface MovieDetailsContract {

    interface View : BaseContract.View {

        fun initUI()

        fun showProgress()

        fun stopProgress()

        fun showToast(toast: String)

        fun setUI(cast: List<MovieDetails>) {}
    }

    interface Presenter : BaseContract.Presenter<View> {

        fun getCastDetails(id: Int)
    }
}