package com.ifenghui.commonlibrary.base.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (createViewModelInter != null)
            return createViewModelInter?.createViewModel(modelClass)!!
//        return if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
//            SplashViewModel(mApplication, SplashModel(mApplication)) as T
//        }
        else
            throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    private var createViewModelInter: CreateViewModelInter? = null
    open fun addCreaterListener(application: Application,interf: CreateViewModelInter?): BaseFactory?{
        INSTANCE?.createViewModelInter=interf
        return INSTANCE?: BaseFactory(application)
    }
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: BaseFactory? = null

        fun getInstance(application: Application): BaseFactory {
            if (INSTANCE == null) {
                synchronized(BaseFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = BaseFactory(application)
                    }
                }
            }
            return INSTANCE ?: BaseFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}