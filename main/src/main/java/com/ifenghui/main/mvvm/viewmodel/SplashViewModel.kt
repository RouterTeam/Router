package com.ifenghui.main.mvvm.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.ifenghui.apilibrary.api.entity.SplashResult
import com.ifenghui.apilibrary.api.net.ResponseThrowable
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.main.mvvm.model.SplashModel

open class SplashViewModel(@NonNull application: Application, model: SplashModel) :
    BaseViewModel<SplashModel>(application, model) {
    var mNewsDetails: MutableLiveData<SplashResult.SplashData> = MutableLiveData()

    fun getSplashData() {
        accept(mModel.getSplashData()?.subscribe ({ model ->
//            mNewsDetails.set(model.indexAds)
            mNewsDetails.value=model.indexAds
        },{error->
//            if (error is ResponseThrowable){
//                Log.e("========","错误"+error.code)
//            }
//            Log.e("========","错误")
        },{
//            Log.e("========","完成")
        },{
//            Log.e("========","开始")
        }))
    }

    fun itemClick(url :String){
        Log.e("------","----数据=="+url)
    }
}