package com.kgxl.common.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by zjy on 2019-05-16
 * 请求头拦截器
 */
class HeaderInterceptor(private val headers: HashMap<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        headers.entries.forEach {
            newBuilder.addHeader(it.key, it.value)
        }
        return chain.proceed(newBuilder.build())
    }
}