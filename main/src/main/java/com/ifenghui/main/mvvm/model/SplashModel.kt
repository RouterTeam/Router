package com.ifenghui.main.mvvm.model

import android.app.Application
import com.ifenghui.apilibrary.api.CommonService
import com.ifenghui.apilibrary.api.entity.SplashResult
import com.ifenghui.apilibrary.api.net.RxUtils.exceptionTransformer
import com.ifenghui.apilibrary.api.net.RxUtils.rxSchedulerHelper
import com.ifenghui.commonlibrary.base.model.BaseModel
import com.ifenghui.commonlibrary.net.rx.RetrofitHelper
import io.reactivex.Observable

open class SplashModel : BaseModel {
    private var mCommonService: CommonService? = null

    constructor(application: Application) : super(application) {
        mCommonService = RetrofitHelper.getInstance(application).getCommonService()
    }


    fun getSplashData(): Observable<SplashResult>? {
        return mCommonService?.getSplashData()?.compose(exceptionTransformer())
            ?.compose(rxSchedulerHelper())
    }
}