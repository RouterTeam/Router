package com.ifenghui.commonlibrary.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Process
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat

/**
 * Created by Administrator on 2017/5/24 0024.
 */

class PhoneManager {
    companion object {

        /**
         * 浸入式状态栏实现同时取消5.0以上的阴影
         */
        fun setStatusBar(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
                val decorView = window.decorView
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                val option =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                decorView.systemUiVisibility = option
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
                val localLayoutParams = window.attributes
                localLayoutParams.flags =
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
            }
        }

        /**
         * 根据色值切换状态栏背景色
         */
        fun changeStatusBarColor(activity: Activity?, statusBarColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0（含）以上设备生效
                if (activity != null) {
                    activity.window?.statusBarColor =
                        ContextCompat.getColor(activity, statusBarColor)
                }
                return
            }
            //4.4k-4.4w使用侧方法生效
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                setTranslucentStatusBar(activity, statusBarColor)
            }
        }

        /**
         * 改标状态栏背景颜色
         */
        @SuppressLint("ObsoleteSdkInt")
        fun setTranslucentStatusBar(activity: Activity?, statusBarColor: Int) {
            val rootContent = activity?.window?.decorView?.findViewById<View>(android.R.id.content)
            if (rootContent != null) {
                val rootView = (rootContent as ViewGroup).getChildAt(0)
                if (rootView != null) {
                    (rootView as ViewGroup).clipToPadding = true
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                        rootView.fitsSystemWindows = true
                    }
                }
            }
            if (activity != null) {
                //透明状态栏
                activity.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                val tintManager = SystemBarTintManager(activity)
                tintManager.isStatusBarTintEnabled = true
                tintManager.setStatusBarTintResource(statusBarColor)
            }
        }


        /**
         * 已知系统类型时，设置状态栏黑色字体图标。
         * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
         */
        fun setStatusBarLightMode(activity: Activity?, window: Window, dark: Boolean) {
            val type = getStatusBarLightMode(window)
            if (type == 1) {//小米机型
                MIUISetStatusBarLightMode(window, !dark)
            } else if (type == 2) {//魅族机型
                FlymeSetStatusBarLightMode(window, !dark)
            } else if (type == 3) {//6.0及以上机型
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or if (dark) View.SYSTEM_UI_FLAG_LAYOUT_STABLE else View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0含以上6.0以下机型
                    window.statusBarColor = Color.parseColor("#33000000")
                } else {//4.4含以上5.0以下机型
                    val tintManager = SystemBarTintManager(activity)
                    tintManager.isStatusBarTintEnabled = true
                    tintManager.setStatusBarTintColor(Color.parseColor("#33000000"))
                }
            }
        }

        /**
         * 设置状态栏黑色字体图标，
         * 适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
         * @return 1:MIUUI 2:Flyme 3:android6.0
         */
        fun getStatusBarLightMode(window: Window): Int {
            var result = 0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> result = 3
                    MIUISetStatusBarLightMode(window, true) -> result = 1
                    FlymeSetStatusBarLightMode(window, true) -> result = 2
                    else -> {//5.0

                    }
                }
            }
            return result
        }


        /**
         * 改变底部状态栏的颜色
         *
         * @param activity
         * @param window
         */
        fun setNavigationBarColor(activity: Activity?, window: Window, navigationBarTintColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (activity!=null){
                    window.navigationBarColor =ContextCompat.getColor(activity, navigationBarTintColor)
                }
            } else {
                if (activity!=null) {
                    val tintManager = SystemBarTintManager(activity)
                    tintManager.setNavigationBarTintEnabled(true)
                    // tintManager.setNavigationBarTintColor(Color.parseColor("#ffffff"));
                    tintManager.setTintColor(ContextCompat.getColor(activity, navigationBarTintColor))
                }
            }
        }


        /**
         * 清除MIUI或flyme或6.0以上版本状态栏黑色字体
         */
        fun StatusBarDarkMode(window: Window) {
            when (getStatusBarLightMode(window)) {
                1 -> MIUISetStatusBarLightMode(window, false)
                2 -> FlymeSetStatusBarLightMode(window, false)
                3 -> window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }

        /**
         * 设置状态栏图标为深色和魅族特定的文字风格
         * 可以用来判断是否为Flyme用户
         * @param window 需要设置的窗口
         * @param dark   是否把状态栏字体及图标颜色设置为深色
         * @return boolean 成功执行返回true
         */
        fun FlymeSetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
            var result = false
            if (window != null) {
                try {
                    val lp = window.attributes
                    val darkFlag =
                        WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                    val meizuFlags =
                        WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
                    darkFlag.isAccessible = true
                    meizuFlags.isAccessible = true
                    val bit = darkFlag.getInt(null)
                    var value = meizuFlags.getInt(lp)
                    value = if (dark) {
                        value or bit
                    } else {
                        value and bit.inv()
                    }
                    meizuFlags.setInt(lp, value)
                    window.attributes = lp
                    result = true
                } catch (e: Exception) {

                }
            }
            return result
        }

        /**
         * 设置状态栏字体图标为深色，需要MIUIV6以上
         * @param window 需要设置的窗口
         * @param dark   是否把状态栏字体及图标颜色设置为深色
         * @return boolean 成功执行返回true
         */
        fun MIUISetStatusBarLightMode(window: Window?, dark: Boolean): Boolean {
            var result = false
            if (window != null) {
                val clazz = window.javaClass
                try {

                    val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                    val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                    var darkModeFlag = field.getInt(layoutParams)
                    val extraFlagField = clazz.getMethod(
                        "setExtraFlags",
                        Int::class.javaPrimitiveType,
                        Int::class.javaPrimitiveType
                    )
                    if (dark) {
                        extraFlagField.invoke(window, darkModeFlag, darkModeFlag)//状态栏透明且黑色字体
                    } else {
                        extraFlagField.invoke(window, 0, darkModeFlag)//清除黑色字体
                    }
                    result = true
                } catch (e: Exception) {

                }
            }
            return result
        }

        /**
         * 是否是主进程
         */
        fun isMainProcess(context: Context): Boolean {
            val pid = Process.myPid()
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            for (appProcess in activityManager.runningAppProcesses) {
                if (appProcess.pid == pid) {
                    return context.applicationInfo.packageName == appProcess.processName
                }
            }
            return false
        }
    }
}
