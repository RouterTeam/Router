package com.ifenghui.home.mvvm.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.databinding.ObservableArrayList
import com.ifenghui.apilibrary.api.entity.HomeResult
import com.ifenghui.apilibrary.api.entity.HomeTitle
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.home.mvvm.model.HomeModel

class HomeViewModel(@NonNull application: Application, model: HomeModel) :
    BaseViewModel<HomeModel>(application, model) {
    var list: ObservableArrayList<Any> = ObservableArrayList()
    /**
     * 获取首页数据
     */
    fun getHomeData() {
        accept(mModel.getHomeData()?.subscribe({ model ->

            if (model.status?.code != 1) {

            }

            //添加banner 数据
            if (model.ads!=null){
                list.add(model.ads)
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
                    list.addAll(item.storys)
                }
            }

            //添加课程数据
            if (model.lessonIndex != null) {
                addTitle(model.lessonIndex, false)
                list.addAll(model.lessonIndex.lessonList)

            }

            //添加经典故事
            if (model.classicAndCreateGroup != null) {
                model.classicAndCreateGroup.forEach { item ->
                    addTitle(item, true)
                    list.addAll(item.storys)
                }
            }

            //添加情感教育故事
            if (model.emotionAndHumourGroup != null) {
                model.emotionAndHumourGroup.forEach { item ->
                    addTitle(item, true)
                    list.addAll(item.storys)
                }
            }

            //添加传统文化故事
            if (model.traditionCultureGroup != null) {
                model.traditionCultureGroup.forEach { item ->
                    addTitle(item, true)
                    list.addAll(item.storys)
                }
            }

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

    /**
     * 添加title
     */
    private fun addTitle(group: HomeResult.HomeItemGroup?, isNeedMore: Boolean) {
        var title = HomeTitle()
        title.name = group?.name
        title.content = group?.content
        title.targetType = group?.targetType ?: 0
        title.targetValue = group?.targetValue ?: 0
        title.isNeedMore = isNeedMore
        list.add(title)
    }

}
