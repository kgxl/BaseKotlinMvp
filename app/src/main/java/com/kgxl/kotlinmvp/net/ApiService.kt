package com.kgxl.kotlinmvp.net

import com.kgxl.common.base.BaseBean
import com.kgxl.kotlinmvp.bean.BookBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by zjy on 2019-05-14
 * 所有接口
 */
interface ApiService {
    @GET("v2/book/search")
    fun search(@Query("q") bookName: String,
               @Query("apikey") appkey:String="0df993c66c0c636e29ecbb5344252a4a"):Observable<BaseBean<List<BookBean>>>
}