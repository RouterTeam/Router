package com.ifenghui.home.mvvm.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.ifenghui.apilibrary.api.entity.HomeResult
import com.ifenghui.apilibrary.api.entity.HomeTitle
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.commonlibrary.binding.listenener.InverseBindingListener
import com.ifenghui.home.R
import com.ifenghui.home.mvvm.model.HomeModel
import com.ifenghui.home.ui.activity.TouchStorysActivity

class HomeViewModel(@NonNull application: Application, model: HomeModel) :
    BaseViewModel<HomeModel>(application, model) {
    //    var list: ObservableArrayList<Any> = ObservableArrayList()
    var listData: MutableLiveData<ArrayList<Any>> = MutableLiveData()
    val refreshing: MutableLiveData<InverseBindingListener> = MutableLiveData()
    val refreshStatus: MutableLiveData<Boolean> = MutableLiveData()
    val storyFly:MutableLiveData<Int> = MutableLiveData()

    init {
        refreshing.value = object :
            InverseBindingListener {
            override fun onChange() {
                getHomeData(false)
            }
        }
        storyFly.value= R.mipmap.home_story_fly
    }

    /**
     * 获取首页数据
     */
    fun getHomeData(isNeedLoading: Boolean) {
        accept(mModel.getHomeData()?.subscribe({ model ->

            if (listData?.value == null)
                listData?.value = ArrayList()
            listData?.value?.clear()
            //添加banner 数据
            if (model.ads != null) {
                listData?.value?.add(model.ads)
            }


            //添加精品推荐数据
//            if (model.recommendGroup != null && model.recommendGroup.ads != null) {
//                addTitle(model.recommendGroup, false)
//                list.add(model.recommendGroup.ads)
//            }

            //添加同步数据
            if (model.newStoryGroup != null) {
                model.newStoryGroup.forEach { item ->
                    addTitle(item, true)
                    listData?.value?.addAll(item.storys)
                }
            }

            //添加课程数据
            if (model.lessonIndex != null) {
                addTitle(model.lessonIndex, false)
                listData?.value?.addAll(model.lessonIndex.lessonList)

            }

            //添加经典故事
            if (model.classicAndCreateGroup != null) {
                model.classicAndCreateGroup.forEach { item ->
                    addTitle(item, true)
                    listData?.value?.addAll(item.storys)
                }
            }

            //添加情感教育故事
            if (model.emotionAndHumourGroup != null) {
                model.emotionAndHumourGroup.forEach { item ->
                    addTitle(item, true)
                    listData?.value?.addAll(item.storys)
                }
            }

            //添加传统文化故事
            if (model.traditionCultureGroup != null) {
                model.traditionCultureGroup.forEach { item ->
                    addTitle(item, true)
                    listData?.value?.addAll(item.storys)
                }
            }

        }, { error -> //请求出错
            if (listData?.value?.size == 0)
                postShowErrStatusViewEvent()
        }, { //请求结束
            if (listData?.value?.size != 0)
                postCompleteLoadingViewEvent()
            refreshStatus.value = true
        }, {//请求开始
            if (isNeedLoading && listData?.value?.size == 0) {
                postShowTransLoadingViewEvent(true)
            } else {
                refreshStatus.value = false
            }
        }))
    }


    /**
     * 添加title
     */
    private fun addTitle(group: HomeResult.HomeItemGroup?, isNeedMore: Boolean) {
        var title = HomeTitle()
        title.name = group?.name
        title.content = group?.content
        title.targetType = group?.targetType ?: 0
        title.targetValue = group?.id ?: 0
        title.isNeedMore = isNeedMore
        listData?.value?.add(title)
    }

    /**
     *
     */
    fun touchStoryClick(){
        postStartActivityEvent(TouchStorysActivity::class.java,null,false)
    }

}
