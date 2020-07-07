package com.colin.shelf.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.colin.shelf.ui.fragment.ShelfFragment
import com.ifenghui.commonlibrary.application.Constance
import com.ifenghui.commonlibrary.provider.IMainProvider

@Route(path = Constance.FRAGMENT_URL_SHELF_PAGE,name = "书架")
class ShelfProvider : IMainProvider {
    /**
     * 获取fragment实例
     */
    override fun getMainFragment(): Fragment? {
        return ShelfFragment.newInstance()
    }
    /**
     * 分发标志到对应的页面
     */
    override fun distribution(context: Context?, flag: String?, vararg objects: Any?) {

    }
}