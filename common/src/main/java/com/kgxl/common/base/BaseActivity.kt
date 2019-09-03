package com.kgxl.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by zjy on 2019-05-13
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView()
        setContentView(getLayoutResId())
        initView()
        initListener()
        initData()
    }

    abstract fun initData()

    abstract fun initView()

    abstract fun initListener()

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun beforeSetContentView()
}