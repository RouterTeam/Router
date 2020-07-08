package com.colin.shelf.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colin.shelf.BR
import com.colin.shelf.R
import com.colin.shelf.databinding.FragmentShelfLayoutBinding
import com.colin.shelf.mvvm.factory.ShelfViewModelFactory
import com.colin.shelf.mvvm.model.ShelfModel
import com.colin.shelf.mvvm.viewmodel.ShelfViewModel
import com.ifenghui.commonlibrary.base.ui.fragment.BaseFragment

class ShelfFragment: BaseFragment<FragmentShelfLayoutBinding, ShelfViewModel>() {

    override fun onBindLayout(): Int {
       return R.layout.fragment_shelf_layout
    }

    override fun onBindViewModel(): Class<ShelfViewModel> {
        return ShelfViewModel::class.java
    }

    override fun onBindVariableId(): Int {
        return BR.shelfViewModel
    }

    override fun <T : ViewModel> createViewModel(modelClass: Class<T>): T {
        return mActivity()?.application?.let { ShelfViewModel(it, ShelfModel(mActivity()?.application!!)) } as T
    }

//    override fun onBindViewModelFactory(): ViewModelProvider.Factory {
//        return ShelfViewModelFactory.getInstance(mActivity()?.application!!)
//    }

    companion object {
        private var INSTANCE:ShelfFragment?=null
        fun newInstance(): Fragment? {
            if (INSTANCE == null) {
                synchronized(ShelfFragment::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ShelfFragment()
                    }
                }
            }
            return INSTANCE ?: ShelfFragment()
        }
    }

    /**
     * 初始化书架
     */
    override fun onCreateDelay(bundle: Bundle?) {

    }

}