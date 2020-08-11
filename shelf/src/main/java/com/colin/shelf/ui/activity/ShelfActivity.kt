package com.colin.shelf.ui.activity

import android.os.Bundle
import com.colin.shelf.R
import com.colin.shelf.ui.fragment.ShelfFragment
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity

class ShelfActivity:BaseLazyActivity() {
    override fun onCreateDelay(bundle: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, ShelfFragment()).commit()

    }

    override fun onBindLayout(): Int {
        return R.layout.activity_shelf_layout
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