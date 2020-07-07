package com.ifenghui.commonlibrary.utils

import android.content.Context
import android.os.Parcelable
import com.tencent.mmkv.MMKV

object PreferencesManager {

    /**
     * 初始化
     */
    @JvmStatic
    fun initPreferencesManager(context: Context) {
        MMKV.initialize(context)
    }

    /**
     * 保存数据
     */
    @JvmStatic
    fun savePreferencesFlag(key: String, value: Any) {
        val defaultMMKV = MMKV.defaultMMKV()
        when (value) {
            is Int -> {
                defaultMMKV.encode(key, value)
            }
            is Boolean -> {
                defaultMMKV.encode(key, value)
            }
            is Float -> {
                defaultMMKV.encode(key, value)
            }
            is Double -> {
                defaultMMKV.encode(key, value)
            }
            is String -> {
                defaultMMKV.encode(key, value)
            }
            is Long -> {
                defaultMMKV.encode(key, value)
            }
            is Parcelable -> {
                defaultMMKV.encode(key, value)
            }
        }
    }

    /**
     * 保存数据
     */
    @JvmStatic
    fun savePreferencesFlag(key: String, value: Set<String>) {
        val defaultMMKV = MMKV.defaultMMKV()
        defaultMMKV.encode(key, value)
    }

    /**
     * 获取数据 boolean
     */
    @JvmStatic
    fun getPreferencesBoolFlag(key: String): Boolean {
        val defaultMMKV = MMKV.defaultMMKV()
        return defaultMMKV.decodeBool(key)
    }

    /**
     * 获取数据 Long
     */
    @JvmStatic
    fun getPreferencesLongFlag(key: String): Long {
        val defaultMMKV = MMKV.defaultMMKV()
        return defaultMMKV.decodeLong(key)
    }

    /**
     * 获取数据 Int
     */
    @JvmStatic
    fun getPreferencesIntFlag(key: String): Int {
        val defaultMMKV = MMKV.defaultMMKV()
        return defaultMMKV.decodeInt(key)
    }

    /**
     * 获取数据 Float
     */
    @JvmStatic
    fun getPreferencesFloatFlag(key: String): Float {
        val defaultMMKV = MMKV.defaultMMKV()
        return defaultMMKV.decodeFloat(key)
    }

    /**
     * 获取数据 Double
     */
    @JvmStatic
    fun getPreferencesDoubleFlag(key: String): Double {
        val defaultMMKV = MMKV.defaultMMKV()
        return defaultMMKV.decodeDouble(key)
    }

    /**
     * 获取数据 String
     */
    @JvmStatic
    fun getPreferencesStringFlag(key: String): String {
        val defaultMMKV = MMKV.defaultMMKV()
        return defaultMMKV.decodeString(key)
    }

//    fun <T>getPreferencesFlag(key: String,tClass:Class<T> ): T {
//        val defaultMMKV = MMKV.defaultMMKV()
//        return defaultMMKV.decodeParcelable(key, tClass)
//    }

    @JvmStatic
    fun removePreferencesFlag(key: String){
        val defaultMMKV = MMKV.defaultMMKV()
        defaultMMKV.removeValueForKey(key)
    }

    @JvmStatic
    fun clearAllPrefercencesFlag(){
        val defaultMMKV = MMKV.defaultMMKV()
        defaultMMKV.clearAll()
    }

}
