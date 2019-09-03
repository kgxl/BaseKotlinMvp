package com.kgxl.common.widget

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import com.kgxl.common.R

/**
 * Created by zjy on 2019-05-19
 */
class BaseLoadingDialog(context: Context) : Dialog(context, R.style.loading_style) {
    init {
        setContentView(R.layout.layout_loading_dialog)
        // 设置居中
        window!!.attributes.gravity = Gravity.CENTER
        val lp = window!!.attributes
        // 设置背景层透明度
        lp.dimAmount = 0.6f
        window!!.attributes = lp
    }

    override fun show() {
        super.show()
        Log.e("zjy","show")
        // 启动动画
        startAnimation()
    }

    override fun onStop() {
        super.onStop()
        Log.e("zjy","onStop")
        //  停止动画
        findViewById<View>(R.id.net_iv_loading).clearAnimation()
    }

    /** 旋转动画  */
    private fun startAnimation() {
        val rotate = AnimationUtils.loadAnimation(context, R.anim.loading_anim)
        findViewById<View>(R.id.net_iv_loading).startAnimation(rotate)
    }
}