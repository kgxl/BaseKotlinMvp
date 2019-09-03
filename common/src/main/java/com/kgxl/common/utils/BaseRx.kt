package com.kgxl.common.utils

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by zjy on 2019-05-16
 * RxJava工具类
 */
class BaseRx {
    /**
     * io线程转为main线程
     */
    companion object {
        fun <T> io4Main(): ObservableTransformer<T, T> {
            return ObservableTransformer {
                it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
            }
        }
    }
}