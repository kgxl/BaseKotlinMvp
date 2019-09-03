package com.kgxl.common.net

import com.google.gson.GsonBuilder
import com.kgxl.common.BuildConfig
import com.kgxl.common.interceptor.HttpLoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.jetbrains.annotations.Nullable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zjy on 2019-05-16
 */
class RetrofitHelper {
    companion object {
        private val gson by lazy { GsonBuilder().setLenient().create() }
        @Nullable
        fun <T> createRetrofit(builder: Builder<T>): T {
            return Retrofit.Builder()
                .baseUrl(builder.baseUrl)
                .client(okHttpClientProvider(builder.interceptor))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(builder.cls)
        }

        private fun okHttpClientProvider(interceptors: List<Interceptor>?): OkHttpClient {
            val okHttpBuilder = OkHttpClient.Builder()

            if (interceptors != null && interceptors.isNotEmpty()) {
                interceptors.forEach {
                    okHttpBuilder.addInterceptor(it)
                }
            }
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                okHttpBuilder.addInterceptor(httpLoggingInterceptor)
            }
            okHttpBuilder.readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
            return okHttpBuilder.build()
        }
    }

    class Builder<out T>(val baseUrl: String, val cls: Class<out T>, val interceptor: List<Interceptor>? = null) {
        fun create() = RetrofitHelper.createRetrofit(this)
    }
}