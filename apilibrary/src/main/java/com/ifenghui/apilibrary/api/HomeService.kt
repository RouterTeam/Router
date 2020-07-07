package com.ifenghui.apilibrary.api

import com.ifenghui.apilibrary.api.entity.HomeResult
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeService {

    /**
     * 首页数据
     */
    @GET("index/getIndex2_12_0?")
    fun getHomeData(): Observable<HomeResult>
}