package com.kgxl.kotlinmvp.contanst

import com.kgxl.base.IBaseView
import com.kgxl.common.base.BaseBean
import com.kgxl.common.base.BaseObserver

/***
 * Create by zjy on 2019/9/26
 * 为了适配json格式
 */
abstract class RealObserver<T>(private val iBaseView: IBaseView? = null) : BaseObserver<T>(iBaseView) {
    override fun onNext(t: BaseBean<T>) {
        if (t.data != null) {
            success(t.data)
        }
    }
}