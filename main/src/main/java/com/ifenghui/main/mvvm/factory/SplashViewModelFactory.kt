package com.ifenghui.main.mvvm.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ifenghui.main.mvvm.model.SplashModel
import com.ifenghui.main.mvvm.viewmodel.SplashViewModel

class SplashViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            SplashViewModel(mApplication, SplashModel(mApplication)) as T
        } else SplashViewModel(mApplication, SplashModel(mApplication)) as T
        //        else if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
        //            return (T) new NewsListViewModel(mApplication, new NewsListModel(mApplication));
        //        }else if (modelClass.isAssignableFrom(NewsTypeViewModel.class)) {
        //            return (T) new NewsTypeViewModel(mApplication, new NewsTypeModel(mApplication));
        //        }
        //        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: SplashViewModelFactory? = null

        fun getInstance(application: Application): SplashViewModelFactory {
            if (INSTANCE == null) {
                synchronized(SplashViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = SplashViewModelFactory(application)
                    }
                }
            }
            return INSTANCE ?: SplashViewModelFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
