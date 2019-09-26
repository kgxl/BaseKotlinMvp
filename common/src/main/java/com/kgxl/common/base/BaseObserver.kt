package com.kgxl.common.base

import com.kgxl.base.IBaseView
import com.kgxl.common.exception.GlobalExceptionHandle
import io.reactivex.observers.DisposableObserver

/**
 * Created by zjy on 2019-05-16
 *
 */
abstract class BaseObserver<T>(private val iBaseView: IBaseView? = null) :
    DisposableObserver<BaseBean<T>>() {


    override fun onStart() {
        iBaseView?.showDialog()
    }

    override fun onComplete() {
        iBaseView?.hideDialog()
    }


    override fun onNext(t: BaseBean<T>) {
        if (t != null) {
            if (t.status == 200) {
                if (null != t.data) {
                    success(t.data)
                } else {
                    successMsg(t.message)
                }
            } else {
                error(t.status, t.message)
            }
        }
    }

    override fun onError(e: Throwable) {
        iBaseView?.hideDialog()
        error(GlobalExceptionHandle.handleException(e))
    }

    /**
     * 为了适配不规则返回json
     */
    open fun successMsg(msg: String) {

    }

    abstract fun success(data: T)
    abstract fun error(errorCode: Int, errorMsg: String)
}