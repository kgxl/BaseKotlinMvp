package com.kgxl.common.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by zjy on 2019-05-16
 * 请求头拦截器
 */
class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val build = chain.request().newBuilder().addHeader("name", "zjy").build()
        return chain.proceed(build)
    }
}