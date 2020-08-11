package com.colin.magazine.ui.activity

import android.os.Bundle
import com.colin.magazine.R
import com.colin.magazine.ui.fragment.MagazineFragment
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity

class MagazineActivity : BaseLazyActivity() {
    override fun onCreateDelay(bundle: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MagazineFragment()).commit()

    }

    override fun onBindLayout(): Int {
        return R.layout.activity_magazine_layout
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