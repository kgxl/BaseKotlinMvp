package com.kgxl.common.base

import android.os.Bundle
import com.kgxl.base.BaseActivity
import com.kgxl.base.IBaseView

/**
 * Created by zjy on 2019-05-13
 */
abstract class BaseMvpActivity<T : IBaseView> : BaseActivity() {
    private lateinit var presenter: BaseRxPresenter<T>
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = getPresenter()
        super.onCreate(savedInstanceState)
    }
    abstract fun getPresenter(): BaseRxPresenter<T>
}