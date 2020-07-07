package com.ifenghui.commonlibrary.base.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.SkinAppCompatDelegateImpl
import com.colin.skinlibrary.SkinManager
import com.ifenghui.commonlibrary.R
import com.ifenghui.commonlibrary.application.AppConfig
import com.ifenghui.commonlibrary.base.event.BaseEvent
import com.ifenghui.commonlibrary.base.view.IBaseView
import com.ifenghui.commonlibrary.utils.DecoreViewManagerInterf
import com.ifenghui.commonlibrary.utils.PhoneManager
import com.ifenghui.commonlibrary.utils.RouterManger
import com.ifenghui.commonlibrary.utils.TipsViewManagerInterf
import com.ifenghui.commonlibrary.view.ErrorStatusView
import com.ifenghui.commonlibrary.view.LoadingView
import com.ifenghui.commonlibrary.view.NoDataView
import com.ifenghui.commonlibrary.view.NoNetView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.common_root_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import skin.support.widget.SkinCompatSupportable

abstract class BaseLazyActivity : RxAppCompatActivity(), IBaseView, TipsViewManagerInterf,
    DecoreViewManagerInterf , SkinCompatSupportable {
    private var TAG :String?=null
    private var mActivity: RxAppCompatActivity? = null

    override var mRootView: View? = null//布局容器
    override var mNoNetView: NoNetView? = null//无网络提示
    override var mNoDataView: NoDataView? = null//空数据提示
    override var mErrorStatusView: ErrorStatusView? = null//网络状态异常提示
    override var mLoadingView: LoadingView?=null //数据加载提示
    /**
     * 当第一次调用一个Activity就会执行
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            mActivity = this@BaseLazyActivity
            TAG = mActivity?.javaClass?.name
            super.onCreate(savedInstanceState)
            //浸入式状态栏实现同时取消5.0以上的阴影
            changeStatusBar()
            //改变状态栏图标颜色以及顶部底部状态栏底色
            changeStatusBarStatus()
            //填充布局内容
            super.setContentView(R.layout.common_root_layout)
            //初始化提示信息
            initRootView(rootView)
            initToolBar()
            initContentView(findViewById(android.R.id.content))
            //ARouter inject 注入
            RouterManger.injectAouter(this)
            //绑定事件
            bindListener()
            //初始化数据
            onCreateDelay(savedInstanceState)
            //注册eventbus
            EventBus.getDefault().register(this)
        } catch (e: Exception) {
            Log.e(TAG,e.toString())
        } catch (e: Error) {
            Log.e(TAG,e.toString())
        }
    }

    /**
     * 获取mActivity
     */
    override fun mActivity(): Activity? {
        try {
            if (mActivity == null || mActivity?.isFinishing == true) {
                mActivity = this@BaseLazyActivity
            }
        } catch (e: Exception) {
        }
        return mActivity
    }

    /**
     * 浸入式状态栏实现同时取消5.0以上的阴影
     */
    private fun changeStatusBar() = try {
        PhoneManager.setStatusBar(window)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 夜间模式使用
     */
    override fun getDelegate(): AppCompatDelegate {
        return try {
            SkinAppCompatDelegateImpl.get(this, this);
        } catch (e: Exception) {
            super.getDelegate()
        } catch (e: Error) {
            super.getDelegate()
        }
    }

    /**
     * 改变状态栏响应的状态
     */
    private fun changeStatusBarStatus() {
        AppConfig.isDarkMode=!SkinManager.checkIsDefaultMode()

        when {
            AppConfig.isDarkMode -> {
                AppConfig.statusBarColor = R.color.coloTopBar_night
                AppConfig.navigationBarColor = R.color.colorBottomBar_night
            }
            else -> {
                AppConfig.statusBarColor = R.color.coloTopBar
                AppConfig.navigationBarColor = R.color.colorBottomBar
            }
        }
        if (isTransStatusBar()) {
            AppConfig.statusBarColor = R.color.transparent
//            AppConfig.navigationBarColor = R.color.transparent
        }
        //改变statusbar字体图片颜色
        changeStatusBarTextColor()
        //改变statusbar背景颜色
        changeStatusBarColor()
        //改变navigationbar背景颜色
        changeNavigatioBarColor()
    }

    /**
     * 监听skin切换
     */
    override fun applySkin() {
        changeStatusBarStatus()
    }

    /**
     * 改变statusbar字体图片颜色
     */
    private fun changeStatusBarTextColor() = try {
        PhoneManager.setStatusBarLightMode(mActivity, window, AppConfig.isDarkMode)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 改变statusbar背景颜色
     */
    private fun changeStatusBarColor() = try {
        PhoneManager.changeStatusBarColor(mActivity, AppConfig.statusBarColor)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 改变navigationbar背景颜色
     */
    private fun changeNavigatioBarColor() = try {
        PhoneManager.setNavigationBarColor(mActivity, window, AppConfig.navigationBarColor)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 是否让顶部状态栏透明
     */
    open fun isTransStatusBar(): Boolean {
        return false
    }


    /**
     * 跳转页面
     */
    protected var ACTIVITY_REQUEST_CODE = 10000

    fun startActivity(clz: Class<*>, bundle: Bundle?, isNeedResult: Boolean) {
        try {
            val intent = Intent(this, clz)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            if (isNeedResult) {
                startActivityForResult(intent, ACTIVITY_REQUEST_CODE)
            } else {
                startActivity(intent)
            }
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     *activity   返回值
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTIVITY_REQUEST_CODE) {

        }
    }


    /**
     * EventBus监听
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun <T> onEvent(event: BaseEvent<T>) = try {
       Log.e("----","mActivity="+mActivity)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 关闭页面
     */
    override fun finishActivity() {
        try {
            finish()
        } catch (e: Exception) {

        } catch (e: Error) {

        }
    }

    /**
     * 页面被注销时调用
     */
    override fun onDestroy() = try {
        super.onDestroy()
        removeAllContentView(mActivity)
        EventBus.getDefault().unregister(this)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    override fun isFragmentLayout(): Boolean {
        return false
    }
}