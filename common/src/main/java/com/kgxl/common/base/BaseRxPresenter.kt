package com.kgxl.common.base

import android.util.Log
import com.kgxl.base.IBasePresenter
import com.kgxl.base.IBaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by zjy on 2019-05-17
 */
abstract class BaseRxPresenter<V : IBaseView>(view: V) : IBasePresenter<V> {
    var mView: V? = null

    init {
        checkNotNull(view) { throw NullPointerException("view not be null") }
        mView = view
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun addSubscription(disposable: Disposable) {
        if (!disposable.isDisposed)
            compositeDisposable.add(disposable)
    }

    private fun clearDisposable() {
        compositeDisposable.clear()
    }


    override fun detachView() {
        clearDisposable()
        mView = null
    }
}