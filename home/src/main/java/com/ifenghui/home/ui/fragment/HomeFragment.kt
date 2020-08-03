package com.ifenghui.home.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.colin.linkedviewpager.LinkedViewPager
import com.ifenghui.commonlibrary.base.ui.fragment.BaseFragment
import com.ifenghui.commonlibrary.utils.ObservableListUtil
import com.ifenghui.commonlibrary.utils.RecyclerViewManagerUtils
import com.ifenghui.home.BR
import com.ifenghui.home.R
import com.ifenghui.home.databinding.FragmentHomeLayoutBinding
import com.ifenghui.home.mvvm.model.HomeModel
import com.ifenghui.home.mvvm.viewmodel.HomeViewModel
import com.ifenghui.home.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home_layout.*


@Suppress("DEPRECATION")
class HomeFragment : BaseFragment<FragmentHomeLayoutBinding, HomeViewModel>() {
    companion object {

        private var INSTANCE: HomeFragment? = null
        fun newInstance(): Fragment? {
            if (INSTANCE == null) {
                synchronized(HomeFragment::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = HomeFragment()
                    }
                }
            }
            return INSTANCE ?: HomeFragment()
        }
    }

    override fun onBindLayout(): Int {
        return R.layout.fragment_home_layout
    }

    override fun onBindViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.homeViewModel
    }

    override fun <T : ViewModel> createViewModel(modelClass: Class<T>): T {
        return  mActivity()?.application?.let { HomeViewModel(it, HomeModel(mActivity()?.application!!)) } as T
    }

    private var homeAdapter: HomeAdapter? = null

    /**
     * 初始化数据
     */
    override fun onCreateDelay(bundle: Bundle?) {
        mViewModel?.getHomeData()
        RecyclerViewManagerUtils.setGridLayoutManager(mBinding?.recyclerView, mActivity(), 6)
        homeAdapter = HomeAdapter(mActivity())
        homeAdapter?.setDatas(mViewModel?.list)
        mViewModel?.list?.addOnListChangedCallback(
            ObservableListUtil.getListChangedCallback(
                homeAdapter
            )
        )
        mBinding?.recyclerView?.adapter = homeAdapter

        mBinding?.smartrefreshlayout?.setOnRefreshListener {
            smartrefreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
        }
        var hotList = ArrayList<String>()
        hotList.add("小蚊子安琪")
        hotList.add("莴苣姑娘")
        hotList.add("百事通爸爸")
        hotList.add("两头狮子的午餐")
        hotList.add("阿扎姆与公主")
        hotList.add("小狐狸饿坏了")
        mBinding?.tvVertical?.setTextList(hotList)
        mBinding?.tvVertical?.setTextStillTime(4000)//设置停留时长间隔
        mBinding?.tvVertical?.setAnimTime(600)//设置进入和退出的时间间隔

    }

    /**
     * 绑定事件
     */
    override fun bindListener() {
        super.bindListener()
//        mBinding?.recyclerView?.addOnScrollListener(object :OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    mFragment()?.let { GlideApp.with(it).resumeRequests() }//恢复Glide加载图片
//                }else {
//                    mFragment()?.let { GlideApp.with(it).pauseRequests() }//禁止Glide加载图片
//                }
//            }
//        })
    }

    /**
     * 重新加载数据
     */
    override fun onReloadData() {
        super.onReloadData()
        mViewModel?.getHomeData()
    }

    /**
     * 刷新界面使用
     */
    override fun refreshMainData() {
        super.refreshMainData()
        homeAdapter?.notifyDataSetChanged()
    }

    /**
     * 顶部状态栏占位
     */
    override fun enableTopNav(): Boolean {
        return true
    }

    /**
     * 监听是否可见
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        notifyBannerStatus(isVisibleToUser)
    }

    /**
     * 获取焦点
     */
    override fun onResume() {
        super.onResume()
        notifyBannerStatus(true)
    }

    /**
     * 失去焦点
     */
    override fun onPause() {
        super.onPause()
        notifyBannerStatus(false)
    }

    /**
     * 舒心banner状态
     */
    private fun notifyBannerStatus(isVisibleToUser: Boolean) {
        try {
            LinkedViewPager.isActivite = isVisibleToUser
            homeAdapter?.notifyItemChanged(0)
            if (isVisibleToUser)
                mBinding?.tvVertical?.startAutoScroll()
            else
                mBinding?.tvVertical?.stopAutoScroll()
        } catch (e: Exception) {
        }
    }
}
