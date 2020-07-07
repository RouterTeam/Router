package com.ifenghui.apilibrary.api.config

import com.ifenghui.apilibrary.BuildConfig

class NetConfig {
    companion object {
        val SS_TOKEN = "sstoken"
        private val TEST_SERVER = "test_server"//测试服
        private val OFFICIAL_SERVER = "official_server"//正式服
        private val BEAT_SERVER = "beat_server"//beat服
        private val LOCAL_SERVER = "local_server"//本地服
        private var BASE_URL = AppApi.OFFICIAL_URL//服务器标示

        /**
         * 获取baseurl
         */
        fun getBaseUrl(): String {
            switchServer()
            return BASE_URL
        }

        /**
         * 判断是否是测试版本
         */
        private fun isTestVersion(): Boolean {
            try {
                return BuildConfig.VERSION_NAME < "1"
            } catch (e: Exception) {
            }
            return false
        }

        /**
         * 切换对应服务器
         */
        private fun switchServer() {
            //测试版本标识
            if (isTestVersion()) {//测试版本
                //测试正式服标识
                val serverStatus = ""
                when (serverStatus) {
                    OFFICIAL_SERVER -> {//选中正式服
                        BASE_URL = AppApi.OFFICIAL_URL
                    }
                    TEST_SERVER -> {//测试服
                        BASE_URL = AppApi.TEST_URL

                    }
                    BEAT_SERVER -> {//beat服
                        BASE_URL = AppApi.BEAT_URL

                    }
                    LOCAL_SERVER -> {//本地服
                        BASE_URL = AppApi.LOCAL_URL
                    }
                }
            } else {
                BASE_URL = AppApi.OFFICIAL_URL
            }
        }
    }
}