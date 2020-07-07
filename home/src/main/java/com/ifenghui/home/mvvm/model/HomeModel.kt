package com.ifenghui.home.mvvm.model

import android.app.Application
import com.ifenghui.apilibrary.api.HomeService
import com.ifenghui.apilibrary.api.entity.HomeResult
import com.ifenghui.apilibrary.api.net.RxUtils
import com.ifenghui.apilibrary.api.net.RxUtils.rxSchedulerHelper
import com.ifenghui.commonlibrary.base.model.BaseModel
import com.ifenghui.commonlibrary.net.rx.RetrofitHelper
import io.reactivex.Observable

class HomeModel: BaseModel {
    private var mHomeService: HomeService? = null

    constructor(application: Application) : super(application) {
        mHomeService = RetrofitHelper.getInstance(application).getHomeService()
    }

    /**
     * 获取首页数据
     */
    fun getHomeData(): Observable<HomeResult>? {
        return mHomeService?.getHomeData()?.compose(RxUtils.exceptionTransformer())
            ?.compose(rxSchedulerHelper())
    }
}