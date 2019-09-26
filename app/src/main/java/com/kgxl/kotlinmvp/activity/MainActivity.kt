package com.kgxl.kotlinmvp.activity

import android.widget.TextView
import android.widget.Toast
import com.kgxl.common.base.BaseMvpActivity
import com.kgxl.common.base.BaseRxPresenter
import com.kgxl.common.utils.bindView
import com.kgxl.common.widget.BaseLoadingDialog
import com.kgxl.kotlinmvp.R
import com.kgxl.kotlinmvp.bean.BookBean
import com.kgxl.kotlinmvp.mvp.TestContract
import com.kgxl.kotlinmvp.mvp.TestRxPresenterImpl

class MainActivity : BaseMvpActivity<TestContract.TestView>(), TestContract.TestView {
    private val mTvContent by bindView<TextView>(R.id.tv_content)
    override fun initData() {
        testPresenterImpl.loadTask()
    }

    override fun getPresenter(): BaseRxPresenter<TestContract.TestView> {
        return testPresenterImpl
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun beforeSetContentView() {
    }

    private val dialog by lazy { BaseLoadingDialog(this) }
    private val testPresenterImpl by lazy { TestRxPresenterImpl(this) }
    override fun showDialog() {
        dialog.show()
    }

    override fun hideDialog() {
        dialog.dismiss()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun loadFailure(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }


    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun loadTaskSuccess(data: List<BookBean>) {
        val stringBuffer = StringBuffer()
        data.forEach {
            stringBuffer.append(it.title + it.publisher + "\n")
        }
        mTvContent.text = stringBuffer.toString()
        Toast.makeText(this, "loadTaskSuccess", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        testPresenterImpl.detachView()
    }
}
