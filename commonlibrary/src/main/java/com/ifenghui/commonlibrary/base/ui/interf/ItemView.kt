package com.ifenghui.commonlibrary.base.ui.interf

import android.view.View
import android.view.ViewGroup

interface ItemView {
    fun onCreateView(parent: ViewGroup?): View?

    fun onBindView(headerView: View?)
}