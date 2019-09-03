package com.kgxl.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zjy on 2019-05-14
 */
abstract class BaseFragment : Fragment() {
    private var layoutView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layoutView == null) {
            layoutView = inflater.inflate(getLayoutId(), null, false)
        }
        return layoutView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    abstract fun init(view: View)

    @LayoutRes
    abstract fun getLayoutId(): Int
}