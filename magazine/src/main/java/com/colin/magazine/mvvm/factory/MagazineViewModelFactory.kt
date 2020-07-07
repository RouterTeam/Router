package com.colin.magazine.mvvm.factory

import android.annotation.SuppressLint
import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.colin.magazine.mvvm.model.MagazineModel
import com.colin.magazine.mvvm.viewmodel.MagazineViewModel

class MagazineViewModelFactory (private val mApplication: Application) : ViewModelProvider.NewInstanceFactory()  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MagazineViewModel::class.java)) {
            MagazineViewModel(mApplication, MagazineModel(mApplication)) as T
        } else MagazineViewModel(mApplication, MagazineModel(mApplication)) as T

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: MagazineViewModelFactory? = null

        fun getInstance(application: Application): MagazineViewModelFactory {
            if (INSTANCE == null) {
                synchronized(MagazineViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = MagazineViewModelFactory(application)
                    }
                }
            }
            return INSTANCE ?: MagazineViewModelFactory(application)
        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}