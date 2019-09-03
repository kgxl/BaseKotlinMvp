package com.kgxl.common.base

import com.kgxl.base.IBaseView
import com.kgxl.common.exception.GlobalExceptionHandle
import io.reactivex.observers.DisposableObserver

/**
 * Created by zjy on 2019-05-16
 *
 */
abstract class BaseObserver<T : BaseBean>(
    private val iBaseView: IBaseView?
) :
    DisposableObserver<T>() {


    override fun onStart() {
        iBaseView?.showDialog()
    }

    override fun onComplete() {
        iBaseView?.hideDialog()
    }


    override fun onNext(t: T) {
        if (t.code == 200) {
            success(t)
        } else {
            otherStateCode(t)
        }
    }

    override fun onError(e: Throwable) {
        iBaseView?.hideDialog()
        error(GlobalExceptionHandle.handleException(e))
    }

    open fun otherStateCode(data: T) {}

    abstract fun success(data: T)
    abstract fun error(errorMsg: String)
}