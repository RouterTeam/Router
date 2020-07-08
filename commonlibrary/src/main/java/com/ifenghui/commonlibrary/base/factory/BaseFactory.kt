package com.ifenghui.commonlibrary.base.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseFactory private constructor() :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (createViewModelInter != null)
            return createViewModelInter?.createViewModel(modelClass)!!
        else
            throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    private var createViewModelInter: CreateViewModelInter? = null
    open fun addCreaterListener(interf: CreateViewModelInter?): BaseFactory?{
        INSTANCE?.createViewModelInter=interf
        return INSTANCE?: BaseFactory()
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: BaseFactory? = null

        fun getInstance(): BaseFactory {
            if (INSTANCE == null) {
                synchronized(BaseFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = BaseFactory()
                    }
                }
            }
            return INSTANCE ?: BaseFactory()
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}