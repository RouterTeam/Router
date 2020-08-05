package com.colin.shelf.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.colin.shelf.mvvm.model.ShelfModel
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

class ShelfViewModel(@NonNull application: Application, model: ShelfModel) :
    BaseViewModel<ShelfModel>(application, model) {
    var listData: MutableLiveData<ArrayList<Any>> = MutableLiveData()

    /**
     * 获取书架书架
     */
    fun getShelfData(pageNo: Int) {
        accept(mModel?.getShelfData(pageNo)?.subscribe({ data ->
            if (listData?.value == null)
                listData?.value = ArrayList()
            if (pageNo == 1)
                listData?.value?.clear()
            if (data?.serialStoryList != null)
                listData?.value?.addAll(data?.serialStoryList)
            if (data?.buyStoryRecords != null)
                listData?.value?.addAll(data?.buyStoryRecords)
        }, { error -> //请求出错
            if (listData?.value?.size == 0) {
                postShowErrStatusViewEvent()
            }
        }, { //请求结束
            if (listData?.value?.size != 0)
                postCompleteLoadingViewEvent()
        }, {//请求开始
            postShowTransLoadingViewEvent(true)
        }))
    }
}