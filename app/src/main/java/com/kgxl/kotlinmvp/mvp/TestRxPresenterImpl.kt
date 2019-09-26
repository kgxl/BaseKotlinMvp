package com.kgxl.kotlinmvp.mvp

import android.util.Log
import com.kgxl.common.base.BaseObserver
import com.kgxl.common.base.BaseRxPresenter
import com.kgxl.common.interceptor.TestInterceptor
import com.kgxl.common.utils.BaseRx
import com.kgxl.kotlinmvp.bean.BookBean
import com.kgxl.kotlinmvp.bean.TestBean
import com.kgxl.kotlinmvp.contanst.RealObserver
import com.kgxl.kotlinmvp.net.NetWorkService
import okhttp3.Interceptor

/**
 * created by zjy on 2019/3/27
 */
class TestRxPresenterImpl(view: TestContract.TestView) : BaseRxPresenter<TestContract.TestView>(view),
    TestContract.TestPresenter {

    override fun loadTask() {
        Log.e("zjy", "loadTask")
        val subscribe = NetWorkService.apiService
            .search("三国演义")
            .compose(BaseRx.io4Main())
            .subscribeWith(object : RealObserver<List<BookBean>>(mView) {
                override fun success(data: List<BookBean>) {
                    mView?.loadTaskSuccess(data)
                }

                override fun error(errorCode: Int, errorMsg: String) {
                    mView?.loadFailure(errorMsg)
                }
            })
        addSubscription(subscribe)
    }
}