package com.colin.shelf.mvvm.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colin.shelf.mvvm.model.ShelfModel
import com.colin.shelf.mvvm.viewmodel.ShelfViewModel

class ShelfViewModelFactory (private val mApplication: Application) : ViewModelProvider.NewInstanceFactory()  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ShelfViewModel::class.java)) {
            ShelfViewModel(mApplication, ShelfModel(mApplication)) as T
        } else ShelfViewModel(mApplication, ShelfModel(mApplication)) as T

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ShelfViewModelFactory? = null

        fun getInstance(application: Application): ShelfViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ShelfViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ShelfViewModelFactory(application)
                    }
                }
            }
            return INSTANCE ?: ShelfViewModelFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}