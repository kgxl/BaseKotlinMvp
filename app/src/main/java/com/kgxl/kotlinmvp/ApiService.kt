package com.kgxl.api

import com.kgxl.kotlinmvp.TestBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by zjy on 2019-05-14
 * 所有接口
 */
interface ApiService {
    @GET("v2/book/search")
    fun search(@Query("q") bookName: String,
               @Query("apikey") appkey:String="0df993c66c0c636e29ecbb5344252a4a"):Observable<TestBean>
}