package com.ifenghui.main.mvvm.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifenghui.main.mvvm.model.MainModel
import com.ifenghui.main.mvvm.viewmodel.MainViewModel

class MainViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory()  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(mApplication, MainModel(mApplication)) as T
        } else MainViewModel(mApplication, MainModel(mApplication)) as T
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MainViewModelFactory? = null

        fun getInstance(application: Application): MainViewModelFactory {
            if (INSTANCE == null) {
                synchronized(SplashViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MainViewModelFactory(application)
                    }
                }
            }
            return INSTANCE ?: MainViewModelFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}