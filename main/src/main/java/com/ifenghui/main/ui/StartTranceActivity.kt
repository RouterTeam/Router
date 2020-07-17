package com.ifenghui.main.ui

import android.os.Bundle
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import com.ifenghui.main.R

class StartTranceActivity :BaseLazyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        startActivity(MainActivity2::class.java, null, false)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateDelay(bundle: Bundle?) {
//        mRootView?.postDelayed({
//            finish()
//        },2000)
    }

    override fun onBindLayout(): Int {
//        return R.layout.activity_start
        return 0
    }

    override fun enableToolbar(): Boolean {
        return false
    }

    override fun isTransStatusBar(): Boolean {
        return true
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

}