package com.ifenghui.commonlibrary.base.model

import android.app.Application
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseModel : IBaseModel {
    protected var mApplication: Application? = null
    private var mCompositeDisposable: CompositeDisposable? = null
//    constructor()
    constructor(application: Application) : super() {
        mApplication = application
        mCompositeDisposable = CompositeDisposable()
    }

    fun addSubscribe(disposable: Disposable?) {
        try {
            if (disposable != null) {
                if (mCompositeDisposable == null) {
                    mCompositeDisposable = CompositeDisposable()
                }
                mCompositeDisposable?.add(disposable)
            }
        } catch (e: Exception) {
        }
    }

    fun removeSubscribe(disposable: Disposable?) {
        try {
            if (disposable != null) {
                if (!disposable.isDisposed) {
                    disposable.dispose()
                }
                mCompositeDisposable?.remove(disposable)
            }
        } catch (e: Exception) {
        }
    }

    override fun onCleared() {
        try {
            mCompositeDisposable?.dispose()
            mCompositeDisposable?.clear()
        } catch (e: Exception) {
        }
    }
}