package com.ifenghui.commonlibrary.net.rx

import android.annotation.SuppressLint
import android.app.Application
import android.text.TextUtils
import com.ifenghui.apilibrary.api.CommonService
import com.ifenghui.apilibrary.api.HomeService
import com.ifenghui.apilibrary.api.config.AppApi.Companion.OFFICIAL_URL
import com.ifenghui.apilibrary.api.config.AppApi.Companion.TEST_URL
import com.ifenghui.apilibrary.api.config.NetConfig
import com.ifenghui.apilibrary.api.config.NetConfig.Companion.SS_TOKEN
import com.ifenghui.apilibrary.api.net.BaseInterceptor
import com.ifenghui.apilibrary.api.net.CacheInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RetrofitHelper {
    private var okHttpClient: OkHttpClient? = null
    private var retrofitBuilder: Retrofit.Builder? = null
    private var url = ""
    private var retrofit: Retrofit? = null
    private var mAppclication:Application?=null

    /**
     * 单例方式
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var retrofitHelper: RetrofitHelper? = null

        fun getInstance(application: Application): RetrofitHelper {
            if (retrofitHelper == null) {
                synchronized(RetrofitHelper::class.java) {
                    if (retrofitHelper == null) {
                        retrofitHelper = RetrofitHelper(application)
                    }

                }
            }
            return retrofitHelper ?: RetrofitHelper(application)
        }
    }

    /**
     * 初始化数据
     */
    constructor(application: Application) {
        mAppclication=application
        url = NetConfig.getBaseUrl()
        url = OFFICIAL_URL
//        url = TEST_URL
        initOkHttp()
        initRetrofit()
    }

    /**
     * init Retrofit
     */
    private fun initRetrofit() {
        if (retrofitBuilder == null){
            retrofitBuilder = Retrofit.Builder()
            retrofitBuilder?.client(initOkHttp())
                ?.addConverterFactory(JacksonConverterFactory.create())
                ?.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        }

        switchUrl(url)
    }

    /**
     * 切换baseURL
     */
    private fun switchUrl(url:String) {
        if (retrofitBuilder==null){
            initRetrofit()
        }
        retrofit=retrofitBuilder?.baseUrl(url)?.build()
    }

    /**
     * init okhttp
     */
    private fun initOkHttp(): OkHttpClient? {

        if (okHttpClient==null){
            okHttpClient=OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(BaseInterceptor())
                .addNetworkInterceptor(CacheInterceptor(mAppclication))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }
        return okHttpClient
    }


    /**
     * 创建一个公共服务
     */
    fun getCommonService(): CommonService? {
        return retrofit?.create(CommonService::class.java)
    }

    /**
     * 创建一个首页服务
     */
    fun getHomeService(): HomeService? {
        return retrofit?.create(HomeService::class.java)
    }
}