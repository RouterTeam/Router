package com.ifenghui.home.ui.activity

import android.os.Bundle
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import com.ifenghui.home.R
import com.ifenghui.home.ui.fragment.HomeFragment

class HomeActivity :BaseLazyActivity() {

    override fun onBindLayout(): Int {
        return R.layout.activity_home_layout
    }

    override fun onCreateDelay(bundle: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, HomeFragment()).commit()
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