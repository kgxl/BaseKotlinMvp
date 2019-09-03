package com.kgxl.kotlinmvp

import com.kgxl.base.IBasePresenter
import com.kgxl.base.IBaseView


/**
 * created by zjy on 2019/3/27
 */
interface TestContract {
    interface TestView : IBaseView {
        fun loadTaskSuccess()
        fun loadFailure(errorMsg: String)
    }

    interface TestPresenter : IBasePresenter<TestView> {
        fun loadTask()
    }
}