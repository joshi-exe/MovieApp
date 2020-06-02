package com.yash.movie_app.ui.base

import androidx.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Joshi on 01-06-2020.
 */
abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V>, LifecycleObserver {

    var disposables: CompositeDisposable? = null
    private var view: WeakReference<V>? = null

    override fun getView(): V? = view?.get()

    override fun attachView(view: V) {
        this.view = WeakReference(view)
        this.disposables = CompositeDisposable()
    }

    override fun detachView() {
        view?.clear()
        view = null
        disposables?.dispose()
        disposables?.clear()
        disposables = null
    }

    override fun onCreated() = Unit

    override fun onStarted() = Unit

    override fun onResumed() = Unit

    override fun onDestroyed() = Unit

    override fun onStopped() = Unit

    override fun onPaused() = Unit
}