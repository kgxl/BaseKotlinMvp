package com.kgxl.common.base

import com.kgxl.base.IBaseView
import com.kgxl.common.exception.GlobalExceptionHandle
import io.reactivex.observers.DisposableObserver

/**
 * Created by zjy on 2019-05-16
 *
 */
abstract class BaseObserver<T>(
    private val iBaseView: IBaseView?
) :
    DisposableObserver<BaseBean<T>>() {


    override fun onStart() {
        iBaseView?.showDialog()
    }

    override fun onComplete() {
        iBaseView?.hideDialog()
    }


    override fun onNext(t: BaseBean<T>) {
        if (t.code == 200) {
            t.data?.let { success(it) }
        } else {
            error(t.msg)
        }
    }

    override fun onError(e: Throwable) {
        iBaseView?.hideDialog()
        error(GlobalExceptionHandle.handleException(e))
    }

    abstract fun success(data: T)
    abstract fun error(errorMsg: String)
}