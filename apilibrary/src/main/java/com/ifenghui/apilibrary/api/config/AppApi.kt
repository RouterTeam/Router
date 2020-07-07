package com.ifenghui.apilibrary.api.config

interface AppApi {
    companion object {
        val OFFICIAL_URL = "https://storybook.ifenghui.com/api/"//正式服
        val BEAT_URL = "https://storybook.ifenghui.com/beta/api/"//beat服
        val TEST_URL = "https://test.ifenghui.com/api/"//测试服
        val LOCAL_URL = "http://local.ifenghui.com:8080/api/"//本地服
    }

}