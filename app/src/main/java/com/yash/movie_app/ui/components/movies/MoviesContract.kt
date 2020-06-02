package com.yash.movie_app.ui.components.movies

import com.yash.movie_app.ui.base.BaseContract
import com.yash.movie_app.model.Movies

/**
 * Created by Joshi on 01-06-2020.
 */
interface MoviesContract {

    interface View : BaseContract.View {

        fun initUI()
    }

    interface Presenter : BaseContract.Presenter<View> {
    }
}