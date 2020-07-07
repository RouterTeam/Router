package com.ifenghui.commonlibrary.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

interface IMainProvider : IProvider {
    override fun init(context: Context?) {}
    /**
     * 获取Fragment实例
     */
    fun getMainFragment(): Fragment? {return null}

    /**
     * 每个模块分发标志到对应的页面
     */
    fun distribution(context: Context?, flag: String?, vararg objects: Any?)
}