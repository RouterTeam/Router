package com.colin.mine.mvvm.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colin.mine.mvvm.model.MineModel
import com.colin.mine.mvvm.viewmodel.MineViewModel

class MineViewModelFactory (private val mApplication: Application) : ViewModelProvider.NewInstanceFactory()  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MineViewModel::class.java)) {
            MineViewModel(mApplication, MineModel(mApplication)) as T
        } else MineViewModel(mApplication, MineModel(mApplication)) as T

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MineViewModelFactory? = null

        fun getInstance(application: Application): MineViewModelFactory {
            if (INSTANCE == null) {
                synchronized(MineViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MineViewModelFactory(application)
                    }
                }
            }
            return INSTANCE ?: MineViewModelFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}