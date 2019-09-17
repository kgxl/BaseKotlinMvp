package com.kgxl.common.base

/**
 * Created by zjy on 2019-05-16
 */
open class BaseBean<T>() {
    var code: Int = 0
    var msg: String = ""
    var data: T? = null

}