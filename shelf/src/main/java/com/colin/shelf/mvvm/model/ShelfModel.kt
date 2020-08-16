package com.colin.shelf.mvvm.model

import android.app.Application
import com.ifenghui.apilibrary.api.ShelfService
import com.ifenghui.apilibrary.api.entity.ShelfResult
import com.ifenghui.apilibrary.api.net.RxUtils
import com.ifenghui.commonlibrary.base.model.BaseModel
import com.ifenghui.commonlibrary.net.rx.RetrofitHelper
import io.reactivex.Observable

class ShelfModel : BaseModel {
    private var mShelfService: ShelfService? = null

    constructor(application: Application) : super(application) {
        mShelfService = RetrofitHelper.getInstance(application).getShelfService()
    }

    /**
     * 获取书架数据
     */
    fun getShelfData(pageNo:Int): Observable<ShelfResult>?{
        return mShelfService?.getHasBuyStory("d4ae465e335e7ac2a0d0570eebfad5c7",pageNo,30)?.compose(RxUtils.exceptionTransformer())
            ?.compose(RxUtils.rxSchedulerHelper())
    }
}