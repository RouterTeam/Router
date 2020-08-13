package com.ifenghui.commonlibrary.base.viewmodel

import android.app.Application
import android.os.Bundle
import androidx.core.util.Consumer
import androidx.lifecycle.AndroidViewModel
import com.ifenghui.commonlibrary.base.model.BaseModel
import com.ifenghui.commonlibrary.event.SingleLiveEvent
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<M : BaseModel?>( application: Application, var mModel: M) :
    AndroidViewModel(application), IBaseViewModel, Consumer<Disposable> {
    private var mUIChangeLiveData: UIChangeLiveData? = null
    open fun getUC(): UIChangeLiveData? {
        if (mUIChangeLiveData == null) {
            mUIChangeLiveData = UIChangeLiveData()
        }
        return mUIChangeLiveData
    }

    class UIChangeLiveData: SingleLiveEvent<Any>() {
        private var showInitLoadViewEvent: SingleLiveEvent<Boolean>? = null
        private var showTransLoadingViewEvent: SingleLiveEvent<Boolean>? = null
        private var showNoDataViewEvent: SingleLiveEvent<Boolean>? = null
        private var showNetWorkErrViewEvent: SingleLiveEvent<Boolean>? = null
        private var showErrStatusViewEvent: SingleLiveEvent<Boolean>? = null
        private var startActivityEvent: SingleLiveEvent<Map<String, Any>>? = null
        private var finishActivityEvent: SingleLiveEvent<Void>? = null
        private var completeLoadingEvent: SingleLiveEvent<Void>? = null
        private var onBackPressedEvent: SingleLiveEvent<Void>? = null

        fun getShowInitLoadViewEvent(): SingleLiveEvent<Boolean>? {
            showInitLoadViewEvent=createLiveData(showInitLoadViewEvent)
            return showInitLoadViewEvent
        }

        fun getShowTransLoadingViewEvent(): SingleLiveEvent<Boolean>? {
            showTransLoadingViewEvent=createLiveData(showTransLoadingViewEvent)
            return showTransLoadingViewEvent
        }

        /**
         * 完成数据加载
         */
        fun getCompleteLoadingEvent(): SingleLiveEvent<Void>? {
            completeLoadingEvent=createLiveData(completeLoadingEvent)
            return completeLoadingEvent
        }

        /**
         * 空数据
         */
        fun getShowNoDataViewEvent(): SingleLiveEvent<Boolean>? {
            showNoDataViewEvent=createLiveData(showNoDataViewEvent)
            return showNoDataViewEvent
        }

        /**
         * 网络错误
         */
        fun getShowNetWorkErrViewEvent(): SingleLiveEvent<Boolean>? {
            showNetWorkErrViewEvent=createLiveData(showNetWorkErrViewEvent)
            return showNetWorkErrViewEvent
        }

        /**
         * 数据异常提示
         */
        fun getShowErrStatusViewEvent(): SingleLiveEvent<Boolean>? {
            showErrStatusViewEvent=createLiveData(showErrStatusViewEvent)
            return showErrStatusViewEvent
        }

        fun getStartActivityEvent(): SingleLiveEvent<Map<String, Any>>? {
            startActivityEvent=createLiveData(startActivityEvent)
            return startActivityEvent
        }

        fun getFinishActivityEvent(): SingleLiveEvent<Void>? {
            finishActivityEvent=createLiveData(finishActivityEvent)
            return finishActivityEvent
        }

        fun getOnBackPressedEvent(): SingleLiveEvent<Void>? {
            onBackPressedEvent=createLiveData(onBackPressedEvent)
            return onBackPressedEvent
        }

        private fun <T> createLiveData(liveData: SingleLiveEvent<T>?): SingleLiveEvent<T>? {
            var liveData: SingleLiveEvent<T>? = liveData
            if (liveData == null) liveData = SingleLiveEvent<T>()
            return liveData
        }
    }

    protected open fun <T> createLiveData(liveData: SingleLiveEvent<T>?): SingleLiveEvent<T>? {
        var liveData: SingleLiveEvent<T>? = liveData
        if (liveData == null) liveData = SingleLiveEvent<T>()
        return liveData
    }


    open fun postShowInitLoadViewEvent(show: Boolean) {
        mUIChangeLiveData?.getShowInitLoadViewEvent()?.postValue(show)
    }

    open fun postShowTransLoadingViewEvent(show: Boolean) {
        mUIChangeLiveData?.getShowTransLoadingViewEvent()?.postValue(show)
    }

    /**
     * 空数据
     */
    open fun postShowNoDataViewEvent() {
        mUIChangeLiveData?.getShowNoDataViewEvent()?.postValue(true)
    }

    /**
     * 数据异常
     */
    open fun postShowErrStatusViewEvent() {
        mUIChangeLiveData?.getShowErrStatusViewEvent()?.postValue(true)
    }

    /**
     * 网络异常
     */
    open fun postShowNetWorkErrViewEvent() {
        mUIChangeLiveData?.getShowNetWorkErrViewEvent()?.postValue(true)
    }

    /**
     * 完成加载
     */
    open fun postCompleteLoadingViewEvent() {
        mUIChangeLiveData?.getCompleteLoadingEvent()?.call()
    }

    open fun postStartActivityEvent(clz: Class<*>, bundle: Bundle?,isNeedResult:Boolean) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CLASS] = clz
        params[ParameterField.ISNEED_RESULT]=isNeedResult
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        mUIChangeLiveData?.getStartActivityEvent()?.postValue(params)
    }


    open fun postFinishActivityEvent() {
        mUIChangeLiveData?.getFinishActivityEvent()?.call()
    }


    open fun postOnBackPressedEvent() {
        mUIChangeLiveData?.getOnBackPressedEvent()?.call()
    }


    override fun accept(disposable: Disposable?) {
        mModel?.addSubscribe(disposable)
    }

    public override fun onCleared() {
        super.onCleared()
        mModel?.onCleared()
    }

    object ParameterField {
        var CLASS = "CLASS"
        var ISNEED_RESULT="ISNEED_RESULT"
        var CANONICAL_NAME = "CANONICAL_NAME"
        var BUNDLE = "BUNDLE"
    }
}