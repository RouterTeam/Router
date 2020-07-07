package com.ifenghui.commonlibrary.base.view

import androidx.lifecycle.ViewModelProvider
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

interface IDataBindlingBaseView<VM: BaseViewModel<*>> {
    /**
     * 创建ViewModel
     */
    fun createViewModel(): VM

    /**
     * 获取VM的Class
     */
    fun onBindViewModel(): Class<VM>

    /**
     * 获取需要绑定variable的ID
     */
    fun onBindVariableId(): Int

    /**
     * 工厂模式获取viewModel
     */
    fun onBindViewModelFactory(): ViewModelProvider.Factory
}