package com.colin.shelf.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.colin.shelf.mvvm.model.ShelfModel
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.commonlibrary.binding.listenener.InverseBindingListener
import com.ifenghui.commonlibrary.binding.LoadMoreMenu
import com.ifenghui.commonlibrary.binding.listenener.ScrollListener
import com.ifenghui.commonlibrary.utils.ViewUtils

class ShelfViewModel(@NonNull application: Application, model: ShelfModel) :
    BaseViewModel<ShelfModel>(application, model) {
    var listData: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    val refreshing: MutableLiveData<InverseBindingListener> = MutableLiveData()
    val loadmore: MutableLiveData<InverseBindingListener> = MutableLiveData()
    val refreshStatus: MutableLiveData<Boolean> = MutableLiveData()
    val loadmoreStatus: MutableLiveData<LoadMoreMenu> = MutableLiveData()
    val scrollListener: MutableLiveData<ScrollListener> = MutableLiveData()
    var topViewAlpha : MutableLiveData<Float> = MutableLiveData()
    var currentPageNo = 1
    var currentScrollY=0
    init {
        refreshStatus?.value = false
        loadmoreStatus?.value = LoadMoreMenu.HAS_MORE
        topViewAlpha?.value=0f
        refreshing.value = object :
            InverseBindingListener {
            override fun onChange() {
                currentPageNo = 1
                getShelfData(currentPageNo, false)
            }
        }
        loadmore.value = object :
            InverseBindingListener {
            override fun onChange() {
                getShelfData(currentPageNo, false)
            }
        }

        scrollListener.value=object :ScrollListener{
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                currentScrollY+=dy
                topViewAlpha?.value = currentScrollY * 1.0f / ViewUtils.dip2px(application, 50f)
            }

        }

    }

    /**
     * 获取书架书架
     */
    fun getShelfData(pageNo: Int, isNeedLoading: Boolean) {
        accept(mModel?.getShelfData(pageNo)?.subscribe({ data ->
            if (listData?.value == null)
                listData?.value = ArrayList()
            if (pageNo == 1)
                listData?.value?.clear()
            if (data?.serialStoryList != null)
                listData?.value?.addAll(data?.serialStoryList)
            if (data?.buyStoryRecords != null)
                listData?.value?.addAll(data?.buyStoryRecords)

            if (data?.page?.hasNext == true)
                loadmoreStatus?.value = LoadMoreMenu.HAS_MORE
            else
                loadmoreStatus?.value = LoadMoreMenu.NO_MORE

        }, { error -> //请求出错
            if (listData?.value?.size == 0) {
                postShowErrStatusViewEvent()
            }
            if (pageNo != 1)
                loadmoreStatus?.value = LoadMoreMenu.FAILD
        }, { //请求结束
            currentPageNo++
            refreshStatus?.value = true
            if (listData?.value?.size != 0)
                postCompleteLoadingViewEvent()
        }, {//请求开始
            if (isNeedLoading && pageNo == 1) {
                postShowTransLoadingViewEvent(true)
            } else {
                refreshStatus?.value = false
            }
        }))
    }
}