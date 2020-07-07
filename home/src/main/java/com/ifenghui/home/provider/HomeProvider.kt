package com.ifenghui.home.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ifenghui.commonlibrary.application.Constance
import com.ifenghui.commonlibrary.provider.IMainProvider
import com.ifenghui.home.ui.fragment.HomeFragment

@Route(path = Constance.FRAGMENT_URL_HOME_PAGE)
class HomeProvider :IMainProvider {
    /**
     * 获取fragment实例
     */
    override fun getMainFragment(): Fragment? {
       return HomeFragment.newInstance()
    }

    /**
     * 分发标志到对应的页面
     */
    override fun distribution(context: Context?, flag: String?, vararg objects: Any?) {

    }

}