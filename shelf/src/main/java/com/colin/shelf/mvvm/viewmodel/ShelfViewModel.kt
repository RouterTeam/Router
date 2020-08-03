package com.colin.shelf.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.databinding.ObservableArrayList
import com.colin.shelf.mvvm.model.ShelfModel
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

class ShelfViewModel (@NonNull application: Application, model:ShelfModel): BaseViewModel<ShelfModel>(application,model) {
    var list: ObservableArrayList<Any> = ObservableArrayList()

    /**
     * 获取书架书架
     */
    fun getShelfData(pageNo:Int){
        accept(mModel?.getShelfData(pageNo)?.subscribe({  data->
            if (pageNo==1)
                list.clear()
            if (data?.serialStoryList!=null)
                list.addAll(data?.serialStoryList)
            if (data?.buyStoryRecords!=null)
                list.addAll(data?.buyStoryRecords)
        }, { error -> //请求出错
            if (list.size == 0) {
                postShowErrStatusViewEvent()
            }
        }, { //请求结束
            if (list.size!=0)
                postCompleteLoadingViewEvent()
        }, {//请求开始
            if (list.size==0)
                postShowTransLoadingViewEvent(true)
        }))
    }
}