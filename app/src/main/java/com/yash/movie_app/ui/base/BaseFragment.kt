package com.yash.movie_app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

/**
 * Created by Joshi on 01-06-2020.
 */
abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(),
    BaseContract.View {

    protected var presenter: P? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injectDependencies()

        val viewModel: BaseViewModel<V, P> = ViewModelProviders.of(this).get(
            BaseViewModel<V, P>()::class.java
        )

        viewModel.presenter = viewModel.presenter ?: initPresenter()

        presenter = viewModel.presenter
        presenter?.attachView(this as V)
        presenter?.onCreated()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onStart() {
        super.onStart()
        presenter?.onStarted()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResumed()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPaused()
    }

    override fun onStop() {
        super.onStop()
        presenter?.onStopped()
    }

    protected abstract fun initPresenter(): P

    protected abstract fun injectDependencies()

    abstract fun getLayoutResId(): Int
}