package com.yash.movie_app.di.module

import android.app.Application
import android.content.Context
import com.yash.movie_app.data.DataRepository
import com.yash.movie_app.di.scope.ApplicationScope
import com.yash.movie_app.extensions.RxBus
import com.yash.movie_app.remote.RemoteDataSource
import dagger.Module
import dagger.Provides

/**
 * Created by Joshi on 31-05-2020.
 */

@Module
class ApplicationModule(private val application: Application) {

    @ApplicationScope
    @Provides
    fun providesApplicationContext(): Context = application.applicationContext

    @ApplicationScope
    @Provides
    fun providerRxBus() = RxBus()

    @ApplicationScope
    @Provides
    fun providerDataRepository(remoteDataSource: RemoteDataSource) =
        DataRepository(remoteDataSource)

    @ApplicationScope
    @Provides
    fun provideAppInfo() = AppInfo()
}