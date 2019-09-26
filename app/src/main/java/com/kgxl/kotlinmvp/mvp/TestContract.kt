package com.kgxl.kotlinmvp.mvp

import com.kgxl.base.IBasePresenter
import com.kgxl.base.IBaseView
import com.kgxl.kotlinmvp.bean.BookBean


/**
 * created by zjy on 2019/3/27
 */
interface TestContract {
    interface TestView : IBaseView {
        fun loadTaskSuccess(data:List<BookBean>)
        fun loadFailure(errorMsg: String)
    }

    interface TestPresenter : IBasePresenter<TestView> {
        fun loadTask()
    }
}