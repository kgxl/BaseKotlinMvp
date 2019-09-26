package com.kgxl.kotlinmvp.net

import com.kgxl.common.interceptor.HeaderInterceptor
import com.kgxl.common.net.RetrofitHelper
import com.kgxl.kotlinmvp.contanst.Constant

/***
 * Create by zjy on 2019-05-14
 */
object NetWorkService {
    private val headers by lazy {
        hashMapOf<String, String>()
    }
    val apiService by lazy {
        RetrofitHelper.Builder(Constant.DOMAIN, ApiService::class.java, arrayListOf(
            HeaderInterceptor(headers)
        )).create()
    }
}