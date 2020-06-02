package com.yash.movie_app.ui.components.movies

import com.yash.movie_app.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
class MoviesPresenter @Inject constructor() : BasePresenter<MoviesContract.View>(),
    MoviesContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
    }
}