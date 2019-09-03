package com.kgxl.common.exception

import com.google.gson.JsonParseException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Created by zjy on 2019-05-20
 */
class GlobalExceptionHandle {
    companion object {
        fun handleException(e: Throwable): String {
            return when (e) {
                is ConnectException,
                is UnknownHostException -> "网络连接错误"
                is JsonParseException -> "json解析错误"
                else -> e.message.toString()
            }
        }
    }
}