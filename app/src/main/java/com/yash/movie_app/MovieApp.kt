package com.yash.movie_app

import android.app.Application
import com.yash.movie_app.di.component.ApplicationComponent
import com.yash.movie_app.di.component.DaggerApplicationComponent
import com.yash.movie_app.di.module.ApplicationModule
import com.yash.movie_app.di.module.NetworkModule
import com.yash.movie_app.remote.ApiService

/**
 * Created by Joshi on 01-06-2020.
 */
class MovieApp : Application() {

    val applicationComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule(ApiService.baseUrl, this))
            .build()
    }
}