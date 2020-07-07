package com.colin.mine.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.colin.mine.ui.fragment.MineFragment
import com.ifenghui.commonlibrary.application.Constance
import com.ifenghui.commonlibrary.provider.IMainProvider

@Route(path = Constance.FRAGMENT_URL_MINE_PAGE,name = "我的")
class MineProvider : IMainProvider {
    /**
     * 获取fragment实例
     */
    override fun getMainFragment(): Fragment? {
        return MineFragment.newInstance()
    }
    /**
     * 分发标志到对应的页面
     */
    override fun distribution(context: Context?, flag: String?, vararg objects: Any?) {

    }
}