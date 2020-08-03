package com.ifenghui.apilibrary.api

import com.ifenghui.apilibrary.api.entity.ShelfResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ShelfService {
    /**
     * 获取已购 系列故事和单本故事
     */
    @GET("storySerial/serial_story_list_and_user_has_buy_storys")
    fun getHasBuyStory(
        @Query("token") token: String?,
        @Query("pageNo") pageNo: Int, @Query("pageSize") pageSize: Int
    ): Observable<ShelfResult?>?
}