package com.ifenghui.commonlibrary.base.factory

import androidx.lifecycle.ViewModel

interface CreateViewModelInter {
    fun <T : ViewModel>createViewModel(modelClass: Class<T>): T
}