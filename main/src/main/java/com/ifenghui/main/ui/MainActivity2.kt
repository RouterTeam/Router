package com.ifenghui.main.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ifenghui.commonlibrary.application.BaseApplication
import com.ifenghui.commonlibrary.application.BaseApplication.appVersion
import com.ifenghui.commonlibrary.application.BaseApplication.channelName
import com.ifenghui.commonlibrary.application.Constance.Companion.HOME_FRAGMENT_FLAG
import com.ifenghui.commonlibrary.application.Constance.Companion.MAGAZINE_FRAGMENT_FLAG
import com.ifenghui.commonlibrary.application.Constance.Companion.MINE_FRAGMENT_FLAG
import com.ifenghui.commonlibrary.application.Constance.Companion.SHELF_FRAGMENT_FLAG
import com.ifenghui.commonlibrary.base.ui.PagerAdapter
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import com.ifenghui.commonlibrary.provider.ProviderHelper
import com.ifenghui.main.R
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class MainActivity2 : BaseLazyActivity() {
    private var mFragmentList: ArrayList<Fragment>? = null
    private var mHomeFragment: Fragment? = null
    private var mMagazineFragment: Fragment? = null
    private var mShelfFragment: Fragment? = null
    private var mMineFragment: Fragment? = null


    override fun isTransStatusBar(): Boolean {
        return true
    }

    override fun enableToolbar(): Boolean {
        return false
    }

    override fun onBindLayout(): Int {
        return R.layout.activity_main2
    }

    /**
     * 跳转至闪屏页面
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        startActivity(SplashActivity::class.java, null, false)
        super.onCreate(savedInstanceState)

    }

    /**
     *
     * 初始化数据
     */
    override fun onCreateDelay(bundle: Bundle?) {
        if (bundle != null) {
            mHomeFragment = supportFragmentManager.getFragment(bundle, "Home")
            mMagazineFragment = supportFragmentManager.getFragment(bundle, "Magazine")
            mShelfFragment = supportFragmentManager.getFragment(bundle, "Shelf")
            mMineFragment = supportFragmentManager.getFragment(bundle, "Mine")
        }
        if (mHomeFragment == null)
            mHomeFragment = ProviderHelper.getFragment(HOME_FRAGMENT_FLAG)
        if (mMagazineFragment == null)
            mMagazineFragment = ProviderHelper.getFragment(MAGAZINE_FRAGMENT_FLAG)
        if (mShelfFragment == null)
            mShelfFragment = ProviderHelper.getFragment(SHELF_FRAGMENT_FLAG)
        if (mMineFragment == null)
            mMineFragment = ProviderHelper.getFragment(MINE_FRAGMENT_FLAG)

        mFragmentList = ArrayList()
        mHomeFragment?.let { mFragmentList?.add(it) }
        mMagazineFragment?.let { mFragmentList?.add(it) }
        mShelfFragment?.let { mFragmentList?.add(it) }
        mMineFragment?.let { mFragmentList?.add(it) }
        viewpager?.adapter = PagerAdapter(supportFragmentManager, mFragmentList)
        bottom_navigation?.setSwitchViewPager(viewpager)
        bottom_navigation?.postDelayed({
            bottom_navigation?.visibility = View.VISIBLE
        }, 2000)

        Toast.makeText(mActivity(),"chanel="+channelName+"--version="+appVersion,Toast.LENGTH_LONG).show()
    }

    /**
     * 保存数据
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        try {
            mHomeFragment?.let { supportFragmentManager.putFragment(outState, "Home", it) }
            mMagazineFragment?.let { supportFragmentManager.putFragment(outState, "Magazine", it) }
            mShelfFragment?.let { supportFragmentManager.putFragment(outState, "Shelf", it) }
            mMineFragment?.let { supportFragmentManager.putFragment(outState, "Mine", it) }
        } catch (e: Exception) {
        }
    }

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis()
            } else {
                //小于2000ms则认为是用户确实希望退出程序不调用System.exit()方法进行退出 只是退出到桌面
                var home = Intent(Intent.ACTION_MAIN)
                home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                home.addCategory(Intent.CATEGORY_HOME)
                startActivity(home)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}