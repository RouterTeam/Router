package com.ifenghui.home.mvvm.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifenghui.home.mvvm.model.HomeModel
import com.ifenghui.home.mvvm.viewmodel.HomeViewModel

class HomeViewModelFactory (private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(mApplication, HomeModel(mApplication)) as T
        } else HomeViewModel(mApplication, HomeModel(mApplication)) as T

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: HomeViewModelFactory? = null

        fun getInstance(application: Application): HomeViewModelFactory {
            if (INSTANCE == null) {
                synchronized(HomeViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = HomeViewModelFactory(application)
                    }
                }
            }
            return INSTANCE ?: HomeViewModelFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}