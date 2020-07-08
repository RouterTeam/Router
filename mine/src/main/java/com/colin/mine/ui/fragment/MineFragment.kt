package com.colin.mine.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colin.mine.BR
import com.colin.mine.R
import com.colin.mine.databinding.FragmentMineLayoutBinding
import com.colin.mine.mvvm.factory.MineViewModelFactory
import com.colin.mine.mvvm.model.MineModel
import com.colin.mine.mvvm.viewmodel.MineViewModel
import com.colin.skinlibrary.SkinManager
import com.ifenghui.commonlibrary.application.Constance.Companion.LOGIN_FRAGMENT_FLAG
import com.ifenghui.commonlibrary.base.ui.fragment.BaseFragment
import com.ifenghui.commonlibrary.provider.ProviderHelper
import kotlinx.android.synthetic.main.fragment_mine_layout.view.*
import java.util.*

class MineFragment :
    BaseFragment<FragmentMineLayoutBinding, MineViewModel>() {

    override fun onBindLayout(): Int {
        return R.layout.fragment_mine_layout
    }

    override fun onBindViewModel(): Class<MineViewModel> {
        return MineViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.mineViewModel
    }

    override fun <T : ViewModel> createViewModel(modelClass: Class<T>): T {
        return  mActivity()?.application?.let { MineViewModel(it, MineModel(mActivity()?.application!!)) } as T
    }

//    override fun onBindViewModelFactory(): ViewModelProvider.Factory {
//        return MineViewModelFactory.getInstance(mActivity()?.application!!)
//    }

    companion object {
        private var INSTANCE:MineFragment?=null
        fun newInstance(): Fragment? {
            if (INSTANCE == null) {
                synchronized(MineFragment::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MineFragment()
                    }
                }
            }
            return INSTANCE ?: MineFragment()
        }
    }

    /**
     * 初始化数据
     */
    override fun onCreateDelay(bundle: Bundle?) {
        mViewModel?.getMineData()
        setNightText(!SkinManager.checkIsDefaultMode())
        val list2: MutableList<Int> = ArrayList()
        list2.add(R.mipmap.mine_top_bg0)
        list2.add(R.mipmap.mine_top_bg1)
        list2.add(R.mipmap.mine_top_bg2)
        list2.add(R.mipmap.mine_top_bg3)
        list2.add(R.mipmap.mine_top_bg4)
        mMainView?.gradient?.setImageResources(list2)
    }

    override fun bindListener() {
        super.bindListener()
        mMainView?.uc_avater?.setOnClickListener {
            ProviderHelper.startAct(LOGIN_FRAGMENT_FLAG,mActivity(),"login",null)
        }
        mMainView?.group_night?.setOnClickListener {
            setNightText(SkinManager.checkIsDefaultMode())
            SkinManager.switchSkin(SkinManager.BUILD_IN_NIGHT_MODE_KEY,SkinManager.checkIsDefaultMode())
        }

        mMainView?.tv_get_skin_path?.setOnClickListener {
            SkinManager.applyGrayMode(!SkinManager.isGragyMode)
        }
    }

    fun setNightText(isDark: Boolean) {
        mMainView?.night_tv?.text = if (isDark) "日间" else "夜间"
    }
}