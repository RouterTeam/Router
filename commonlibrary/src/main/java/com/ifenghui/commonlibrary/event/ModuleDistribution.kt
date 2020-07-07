package com.ifenghui.commonlibrary.event

import android.content.Context

/**
 * Description 每个模块分发标志到对应的页面
 */
interface ModuleDistribution {
    fun distribution(context: Context?, flag: String?, vararg objects: Any?)
}