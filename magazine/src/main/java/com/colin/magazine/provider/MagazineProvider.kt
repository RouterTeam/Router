package com.colin.magazine.provider

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.colin.magazine.ui.fragment.MagazineFragment
import com.ifenghui.commonlibrary.application.Constance
import com.ifenghui.commonlibrary.provider.IMainProvider

@Route(path = Constance.FRAGMENT_URL_MAG_PAGE)
class MagazineProvider: IMainProvider {
    /**
     * 获取fragment实例
     */
    override fun getMainFragment(): Fragment? {
        return MagazineFragment.newInstance()
    }
    /**
     * 分发标志到对应的页面
     */
    override fun distribution(context: Context?, flag: String?, vararg objects: Any?) {

    }
}