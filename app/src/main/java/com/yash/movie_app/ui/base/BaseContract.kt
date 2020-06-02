package com.yash.movie_app.ui.base

/**
 * Created by Joshi on 01-06-2020.
 */
interface BaseContract {

    interface View

    interface Presenter<V : View> {

        fun getView(): V?

        fun attachView(view: V)

        fun detachView()

        fun onCreated()

        fun onStarted()

        fun onResumed()

        fun onDestroyed()

        fun onStopped()

        fun onPaused()
    }
}