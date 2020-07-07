package com.ifenghui.commonlibrary.base.view

import android.app.Activity
import android.os.Bundle

interface IBaseView {

    //初始化数据
    fun onCreateDelay(bundle: Bundle?)

    //绑定事件
    fun bindListener(){}

    //获取mActivity
    fun mActivity(): Activity?


}