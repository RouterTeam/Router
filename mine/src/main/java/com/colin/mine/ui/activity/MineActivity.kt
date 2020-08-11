package com.colin.mine.ui.activity

import android.os.Bundle
import com.colin.mine.R
import com.colin.mine.ui.fragment.MineFragment
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity

class MineActivity : BaseLazyActivity() {
    override fun onCreateDelay(bundle: Bundle?) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MineFragment()).commit()

    }

    override fun onBindLayout(): Int {
        return R.layout.activity_mine_layout
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