package com.ifenghui.main.ui

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.ifenghui.commonlibrary.application.Constance
import com.ifenghui.commonlibrary.base.ui.PagerAdapter
import com.ifenghui.commonlibrary.base.ui.activity.BaseActivity
import com.ifenghui.commonlibrary.provider.IMainProvider
import com.ifenghui.main.BR
import com.ifenghui.main.R
import com.ifenghui.main.databinding.ActivityMainBinding
import com.ifenghui.main.mvvm.factory.MainViewModelFactory
import com.ifenghui.main.mvvm.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.bottom_navigation
import kotlinx.android.synthetic.main.activity_main.viewpager
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    @Autowired(name = Constance.FRAGMENT_URL_HOME_PAGE)
    @JvmField
    var iHomeProvider: IMainProvider? = null

    @Autowired(name = Constance.FRAGMENT_URL_MAG_PAGE)
    @JvmField
    var iMagzineProvider: IMainProvider? = null

    @Autowired(name = Constance.FRAGMENT_URL_SHELF_PAGE)
    @JvmField
    var iShelfProvider: IMainProvider? = null

    @Autowired(name = Constance.FRAGMENT_URL_MINE_PAGE)
    @JvmField
    var iMineProvider: IMainProvider? = null

    private var mFragmentList: ArrayList<Fragment>? = null
    private var mHomeFragment: Fragment? = null
    private var mMagazineFragment: Fragment? = null
    private var mShelfFragment: Fragment? = null
    private var mMineFragment: Fragment? = null

    override fun onBindViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.mainViewModel
    }

    override fun onBindViewModelFactory(): MainViewModelFactory {
        return MainViewModelFactory.getInstance(application)
    }

    override fun onBindLayout(): Int {
        return R.layout.activity_main
    }

    override fun isTransStatusBar(): Boolean {
        return true
    }

    override fun enableToolbar(): Boolean {
        return false
    }

    /**
     * 跳转至闪屏页面
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        startActivity(SplashActivity::class.java, null, false)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

    }

    /**
     * 初始化
     */
    override fun onCreateDelay(bundle: Bundle?) {
        super.onCreateDelay(bundle)
        if (bundle != null) {
            mHomeFragment = supportFragmentManager.getFragment(bundle, "Home")
            mMagazineFragment = supportFragmentManager.getFragment(bundle, "Magazine")
            mShelfFragment = supportFragmentManager.getFragment(bundle, "Shelf")
            mMineFragment = supportFragmentManager.getFragment(bundle, "Mine")
        }
        if (mHomeFragment == null)
            mHomeFragment = iHomeProvider?.getMainFragment()
        if (mMagazineFragment == null)
            mMagazineFragment = iMagzineProvider?.getMainFragment()
        if (mShelfFragment == null)
            mShelfFragment = iShelfProvider?.getMainFragment()
        if (mMineFragment == null)
            mMineFragment = iMineProvider?.getMainFragment()

        mFragmentList = ArrayList()
        mHomeFragment?.let { mFragmentList?.add(it)}
        mMagazineFragment?.let { mFragmentList?.add(it)}
        mShelfFragment?.let { mFragmentList?.add(it)}
        mMineFragment?.let { mFragmentList?.add(it)}
        viewpager?.adapter = PagerAdapter(supportFragmentManager, mFragmentList)
        bottom_navigation?.setSwitchViewPager(viewpager)
        bottom_navigation?.postDelayed({
            bottom_navigation?.visibility = View.VISIBLE
        }, 2000)
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