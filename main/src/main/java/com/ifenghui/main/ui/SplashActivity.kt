package com.ifenghui.main.ui

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.ifenghui.commonlibrary.base.ui.activity.BaseActivity
import com.ifenghui.commonlibrary.utils.Callback
import com.ifenghui.commonlibrary.utils.PermissionsManager
import com.ifenghui.main.BR
import com.ifenghui.main.R
import com.ifenghui.main.databinding.ActivitySplashBinding
import com.ifenghui.main.mvvm.factory.SplashViewModelFactory
import com.ifenghui.main.mvvm.model.SplashModel
import com.ifenghui.main.mvvm.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*

@Suppress("DEPRECATION")
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun onBindViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun <T : ViewModel> createViewModel(modelClass: Class<T>): T {
        return SplashViewModel(application, SplashModel(application)) as T
    }

    override fun onBindVariableId(): Int {
        return BR.splashViewModel
    }

    /**
     * 获取布局
     */
    override fun onBindLayout(): Int {
        return R.layout.activity_splash
    }

    /**
     * 初始化
     */
    override fun onCreateDelay(bundle: Bundle?) {
        mViewModel?.getSplashData()
        mActivity()?.let {
            PermissionsManager.requestPermission(
                it, object : Callback<Int>() {
                    override fun call(type: Any?) {
                        if (type is Int && type == 4)
                            iv_ad_cover?.postDelayed({
                                finish()
                            }, 3500)
                    }
                }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                 Manifest.permission.READ_EXTERNAL_STORAGE,
                 Manifest.permission.ACCESS_NETWORK_STATE)
        }

    }


    /**
     * 去除页面自带的效果
     */
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_none, R.anim.slide_none)
    }

    /**
     * 禁止点击物理返回键关闭页面
     */
    override fun onBackPressed() {

    }

    /**
     * 不使用顶部导航栏
     */
    override fun enableToolbar(): Boolean {
        return false
    }

    /**
     * 顶部状态栏透明
     */
    override fun isTransStatusBar(): Boolean {
        return true
    }

}