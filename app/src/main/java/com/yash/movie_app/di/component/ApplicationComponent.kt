package com.yash.movie_app.di.component

import com.yash.movie_app.di.module.ApplicationModule
import com.yash.movie_app.di.module.NetworkModule
import com.yash.movie_app.di.scope.ApplicationScope
import com.yash.movie_app.ui.components.movieDetail.MovieDetailsActivity
import com.yash.movie_app.ui.components.movies.MoviesActivity
import com.yash.movie_app.ui.components.movies.categories.CategoryFragment
import dagger.Component

/**
 * Created by Joshi on 31-05-2020.
 */

@ApplicationScope
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(moviesActivity: MoviesActivity)
    fun inject(categoryFragment: CategoryFragment)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}