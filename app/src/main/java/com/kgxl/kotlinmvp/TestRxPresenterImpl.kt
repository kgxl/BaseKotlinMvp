package com.kgxl.kotlinmvp

import android.util.Log
import com.kgxl.api.ApiService
import com.kgxl.common.base.BaseObserver
import com.kgxl.common.base.BaseRxPresenter
import com.kgxl.common.interceptor.TestInterceptor
import com.kgxl.common.net.RetrofitHelper
import com.kgxl.common.utils.BaseRx
import okhttp3.Interceptor

/**
 * created by zjy on 2019/3/27
 */
class TestRxPresenterImpl(view: TestContract.TestView) : BaseRxPresenter<TestContract.TestView>(view),
    TestContract.TestPresenter {

    override fun loadTask() {
        Log.e("zjy", "loadTask")
        val interactors= listOf<Interceptor>(TestInterceptor())
        val subscribe = RetrofitHelper.Builder("https://api.douban.com/", ApiService::class.java,interactors).create()
            .search("出版社")
            .compose(BaseRx.io4Main())
            .subscribeWith(object : BaseObserver<TestBean>(mView) {
                override fun success(data: TestBean) {
                    mView?.loadTaskSuccess()
                }

                override fun error(errorMsg: String) {
                    mView?.loadFailure(errorMsg)
                }
            })
        addSubscription(subscribe)
    }
}