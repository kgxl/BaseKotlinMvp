package com.kgxl.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.kgxl.common.base.BaseRxPresenter

/**
 * Created by zjy on 2019-05-13
 */
abstract class BaseMvpActivity<T : IBaseView> : BaseActivity(){
    private lateinit var presenter: BaseRxPresenter<T>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = getPresenter()
        initData()
    }


    abstract override fun initData()
    abstract fun getPresenter(): BaseRxPresenter<T>
}