package com.yash.movie_app.ui.components.movieDetail

import android.content.Context
import com.yash.movie_app.R
import com.yash.movie_app.domain.MoviesUseCase
import com.yash.movie_app.extensions.applySchedulers
import com.yash.movie_app.ui.base.BasePresenter
import javax.inject.Inject

/**
 * Created by Joshi on 01-06-2020.
 */
class MovieDetailsPresenter @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    private val context: Context
) : BasePresenter<MovieDetailsContract.View>(),
    MovieDetailsContract.Presenter {

    override fun onCreated() {
        super.onCreated()
        getView()?.initUI()
    }

    override fun getCastDetails(id: Int) {
        disposables?.add(moviesUseCase.getCast(id).applySchedulers()
            .doOnSubscribe { getView()?.showProgress() }
            .doFinally { getView()?.stopProgress() }
            .subscribe({
                if (!it?.cast.isNullOrEmpty()) {
                    getView()?.setUI(it.cast!!)
                } else {
                    getView()?.showToast(context.getString(R.string.something_wrong_fetching_details))
                }
            }, {
                getView()?.showToast(context.getString(R.string.something_wrong))
            })
        )
    }
}