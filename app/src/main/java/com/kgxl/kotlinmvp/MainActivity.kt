package com.kgxl.kotlinmvp

import android.util.Log
import android.widget.Toast
import com.kgxl.base.BaseActivity
import com.kgxl.common.widget.BaseLoadingDialog

class MainActivity : BaseActivity(),TestContract.TestView {
    override fun initData() {
        val aa = listOf(10)
        aa.forEachIndexed { index, i ->

        }
        for (i in 0 until aa.size - 1) {

        }
        testPresenterImpl.loadTask()
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

    override fun loadTaskSuccess() {
        println(test("1221"))
        Log.e("zjy", "loadTaskSuccess")
    }

    override fun onDestroy() {
        super.onDestroy()
        testPresenterImpl.detachView()
    }

    public fun test(c: String): Boolean {
        val a = c.toCharArray()
        for (i in 0 until a.size) {
            if (i >= a.size - i - 1) {
                break;
            }
            if (a[i] != a[a.size - i - 1]) {
                return false
            }
        }
        return true
    }
}
