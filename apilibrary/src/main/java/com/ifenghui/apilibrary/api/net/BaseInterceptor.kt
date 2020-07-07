package com.ifenghui.apilibrary.api.net

import android.text.TextUtils
import com.ifenghui.apilibrary.api.config.NetConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.URLEncoder

class BaseInterceptor : Interceptor {
    private var headers: Map<String, String>? = null
    private val userToken: String? = null

    constructor() {}
    constructor(headers: Map<String, String>?) {
        this.headers = headers
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        // 添加新的参数
        val authorizedUrlBuilder = oldRequest.url
            .newBuilder()
            .scheme(oldRequest.url.scheme)
            .host(oldRequest.url.host)
        if (!TextUtils.isEmpty(userToken)) {//添加公共参数
            authorizedUrlBuilder.addQueryParameter("token", userToken)
        }

        // 新的请求
        val newBuilder = oldRequest.newBuilder()
        newBuilder.method(oldRequest.method, oldRequest.body)
        newBuilder.url(authorizedUrlBuilder.build())
//        if (!TextUtils.isEmpty(userAgent)) {
//            newBuilder.addHeader("User-Agent", userAgent)
//        }
//        if (!TextUtils.isEmpty(channelName)) {
//            newBuilder.addHeader("channel", URLEncoder.encode(channelName, "utf-8"))
//        }
        if (!TextUtils.isEmpty(userToken)) {
            userToken?.let { newBuilder.addHeader(NetConfig.SS_TOKEN, it) }
        }
        if (headers != null && headers?.isNotEmpty()!!) {
            val keys = headers?.keys
            if (keys != null) {
                for (headerKey in keys) {
                    newBuilder.addHeader(headerKey, headers!![headerKey] ?: error("")).build()
                }
            }
        }
        return chain.proceed(newBuilder.build())
    }
}