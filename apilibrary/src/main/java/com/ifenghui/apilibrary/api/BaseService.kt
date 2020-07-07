package com.ifenghui.apilibrary.api

import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface BaseService {

    @GET("")
    fun <T>executeGet(
        @Url url: String?,
        @QueryMap maps: MutableMap<String, String>
    ): Observable<T>

    @POST("{url}")
    fun <T>executePost(
        @Path("url") url: String?,
        @QueryMap maps: Map<String?, String?>?
    ): Observable<T>?

    @POST("{url}")
    fun json(
        @Path("url") url: String?,
        @Body jsonStr: RequestBody?
    ): Observable<ResponseBody?>?

    @Multipart
    @POST("{url}")
    fun upLoadFile(
        @Path("url") url: String?,
        @Part("image\"; filename=\"image.jpg") requestBody: RequestBody?
    ): Observable<ResponseBody?>?

    @POST("{url}")
    fun uploadFiles(
        @Path("url") url: String?,
        @Part("userName") description: String?,
        @PartMap maps: Map<String?, RequestBody?>?
    ): Observable<ResponseBody?>?
}