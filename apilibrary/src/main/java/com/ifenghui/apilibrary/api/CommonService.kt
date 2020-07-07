package com.ifenghui.apilibrary.api

import com.ifenghui.apilibrary.api.dto.RespModel
import com.ifenghui.apilibrary.api.entity.SplashResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CommonService :BaseService{

    /**
     * 检测手机号
     */
    @GET("user/checkPhone?")
    fun checkPhone(@Query("phone") phone: String): Observable<RespModel>

    /**
     * 闪屏页面数据
     */
    @GET("ads/getIndexAds?")
    fun  getSplashData(): Observable<SplashResult>

}