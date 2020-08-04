package com.ifenghui.commonlibrary.utils

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.WindowManager

class ViewUtils {
    companion object{
        @JvmStatic
        fun resideViewWidthAndHeight(view: View?, aspect:Float,type:Int){
            view?.let { it->

                val layoutParams = it.layoutParams
                    var itemWidth = getScreenWidth(it.context)- dip2px(it.context,30f)
                    when(type){
                        1-> itemWidth= (itemWidth-dip2px(it.context,12f))/2
                        2-> itemWidth= (itemWidth-dip2px(it.context,18f))/3
                    }
//                it.post {
                    layoutParams.height= (itemWidth/aspect).toInt()
                    it.layoutParams=layoutParams
//                }

            }
        }

        /**
         * 根据当前设备，将dp转为px
         */
        @JvmStatic
        fun dip2px(context: Context, size: Float): Int {
            try {
                val r = context.resources
                return (TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    size,
                    r.displayMetrics
                ) + 0.5).toInt()
            } catch (e: NullPointerException) {
            } catch (e: Exception) {
            }
            return 0
        }

        /**
         * 获取屏幕宽度
         */
        @JvmStatic
        fun getScreenWidth(context: Context): Int {
            return try {
                val wm = context
                    .getSystemService(Context.WINDOW_SERVICE) as WindowManager
                wm.defaultDisplay.width
            } catch (e: java.lang.Exception) {
                1080
            }
        }

        /**
         * 获取屏幕高度
         */
        @JvmStatic
        fun getScreenHeight(context: Context): Int {
            return try {
                val wm = context
                    .getSystemService(Context.WINDOW_SERVICE) as WindowManager
                wm.defaultDisplay.height
            } catch (e: java.lang.Exception) {
                1920
            }
        }
    }
}