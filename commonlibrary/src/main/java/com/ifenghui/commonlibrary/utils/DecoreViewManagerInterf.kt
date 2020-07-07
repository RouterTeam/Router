@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")

package com.ifenghui.commonlibrary.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

interface DecoreViewManagerInterf {

    /**
     * 实现沉浸式
     */
//    fun fitSystemWindow(context: Activity?) = try {
//        val contentFrameLayout = context?.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
//        contentFrameLayout?.getChildAt(0)?.fitsSystemWindows = true
//    } catch (e: Exception) {
//    } catch (e: Error) {
//    }
    /**
     * 获取状态栏高度——方法2
     * */
    @SuppressLint("PrivateApi")
    fun getStatusBarHeight(context: Activity?): Int {

        var statusBarHeight2 = 0
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                val clazz = Class.forName("com.android.internal.R\$dimen")
                val `object` = clazz.newInstance()
                val height = Integer.parseInt(clazz.getField("status_bar_height").get(`object`).toString())
                statusBarHeight2 = context?.resources?.getDimensionPixelSize(height)!!
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusBarHeight2
    }

    /**
     * 获取窗口根布局
     */
    fun getRootView(context: Activity?): View? {
        try {
            return (context?.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
        } catch (e: Exception) {
        } catch (e: TypeCastException) {

        }
        return null
    }


    /**
     * 获取父布局
     */
    fun getParent(context: Activity?): ViewGroup? {
        try {
            return context?.findViewById<View>(android.R.id.content) as ViewGroup
        } catch (e: Exception) {

        } catch (e: TypeCastException) {

        }
        return null
    }


    /**
     * 移除所有view
     */
    fun removeAllContentView(context: Activity?) = try {
        val parent = getParent(context)
        removeViewResource(parent)
    } catch (e: Exception) {
    }

    /**
     * 移除view资源
     */
    fun removeViewResource(view: View?) = try {
        when (view) {
            is ViewGroup -> releaseViewGroupResource(view)
            is ImageView -> releaseImageViewResouce(view)
//            is RentalsRocketHeaderView -> releaseRefreshResource(view)
//            is RecyclerView -> releaseRecyclerViewResource(view)
        }
        view?.clearAnimation()
        view?.destroyDrawingCache()
    } catch (e: Exception) {
    }

    /**
     * 清空viewgroup资源
     */
    private fun releaseViewGroupResource(view: ViewGroup?) {
        try {
            view ?: return
            (0 until view.childCount)
                //执行方法 转换前过滤掉 为 NULL 的元素
                .mapNotNull { view.getChildAt(it) }
                .forEach { removeViewResource(it) }
            view.removeAllViews()
        } catch (e: Exception) {
        }
    }

    /**
     * 清空imageview图片资源
     */
    private fun releaseImageViewResouce(view: ImageView?) = try {
//        Glide.clear(view)
        view?.setImageResource(0)
        view?.setBackgroundResource(0)
    } catch (e: Exception) {
    }

    /**
     * 清空刷新资源
     */
//    private fun releaseRefreshResource(view: RentalsRocketHeaderView?) = try {
//        view?.resetResource()
//        view?.destroyDrawingCache()
//    } catch (e: Exception) {
//    }

    /**
     * recyclerview释放资源
     */
//    private fun releaseRecyclerViewResource(view: RecyclerView?) {
//        try {
//            view ?: return
//            (0 until view.childCount)
//                //执行方法 转换前过滤掉 为 NULL 的元素
//                .mapNotNull { view.findViewHolderForAdapterPosition(it) }
//                .mapNotNull { it.itemView }
//                .forEach { removeViewResource(it) }
//        } catch (e: Exception) {
//        }
//    }
}