package com.colin.magazine.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.colin.magazine.BR
import com.colin.magazine.R
import com.colin.magazine.databinding.FragmentMagazineLayoutBinding
import com.colin.magazine.mvvm.factory.MagazineViewModelFactory
import com.colin.magazine.mvvm.viewmodel.MagazineViewModel
import com.ifenghui.commonlibrary.base.ui.fragment.BaseFragment

class MagazineFragment : BaseFragment<FragmentMagazineLayoutBinding, MagazineViewModel>() {


    override fun onBindLayout(): Int {
        return R.layout.fragment_magazine_layout
    }

    override fun onBindViewModel(): Class<MagazineViewModel> {
        return MagazineViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.magazineViewModel
    }

    override fun onBindViewModelFactory(): ViewModelProvider.Factory {
        return MagazineViewModelFactory.getInstance(mActivity()?.application!!)
    }

    companion object {
        private var INSTANCE: MagazineFragment? = null
        fun newInstance(): Fragment? {
            if (INSTANCE == null) {
                synchronized(MagazineFragment::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MagazineFragment()
                    }
                }
            }
            return INSTANCE ?: MagazineFragment()
        }
    }

    /**
     * 初始化书架
     */
    override fun onCreateDelay(bundle: Bundle?) {
    }
}