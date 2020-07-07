@file:Suppress("DEPRECATION")

package com.colin.skinlibrary

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import skin.support.SkinCompatManager
import skin.support.app.SkinAppCompatViewInflater
import skin.support.app.SkinCardViewInflater
import skin.support.constraint.app.SkinConstraintViewInflater
import skin.support.content.res.SkinCompatResources
import skin.support.design.app.SkinMaterialViewInflater
import skin.support.utils.SkinPreference

object SkinManager {

    const val BUILD_IN_NIGHT_MODE_KEY = "night"
    const val ASSETS_NIGHT_MODE_KEY = "AssetsNightMode"
    const val SDCARD_NIGHT_MODE_KEY = "SDCardNightMode"
    @JvmStatic
    var isGragyMode=false
    /**
     * 初始化skin
     */
    @JvmStatic
    fun initSkinManager(context: Application) {
        SkinCompatManager.withoutActivity(context)
            //.addStrategy(CustomSDCardLoader())          // 自定义加载策略，指定SDCard路径
            // .addStrategy(ZipSDCardLoader())            // 自定义加载策略，获取zip包中的资源
            .addInflater(SkinAppCompatViewInflater())     // 基础控件换肤
            .addInflater(SkinMaterialViewInflater())      // material design
            .addInflater(SkinConstraintViewInflater())    // ConstraintLayout
            .addInflater(SkinCardViewInflater())          // CardView v7
            //.addInflater(SkinCircleImageViewInflater()) // hdodenhof/CircleImageView
            //.addInflater(SkinFlycoTabLayoutInflater())  // H07000223/FlycoTabLayout
            .setSkinStatusBarColorEnable(false)           // 关闭状态栏换肤，默认打开[可选]
            .setSkinWindowBackgroundEnable(false)         // 关闭windowBackground换肤，默认打开[可选]
            // .setSkinAllActivityEnable(false)           // true: 默认所有的Activity都换肤; false: 只有实现SkinCompatSupportable接口的Activity换肤
            .loadSkin()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    /**
     * 夜间模式切换
     */
    @JvmStatic
    fun switchSkin(skinType:String , isNightMode:Boolean){
        if (isNightMode){
            when(skinType){
                BUILD_IN_NIGHT_MODE_KEY->SkinCompatManager.getInstance().loadSkin("night", null, SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN)
                ASSETS_NIGHT_MODE_KEY-> SkinCompatManager.getInstance().loadSkin("night.skin", null, SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS)
//                SDCARD_NIGHT_MODE_KEY-> SkinCompatManager.getInstance().loadSkin("night.skin", null, CustomSDCardLoader.SKIN_LOADER_STRATEGY_SDCARD)
            }
        }else {
            SkinCompatManager.getInstance().restoreDefaultTheme()
        }

    }

    @JvmStatic
    fun applyGrayMode(grayMode:Boolean){
        this.isGragyMode= grayMode
        switchSkin(SkinCompatManager.getInstance().curSkinName,!checkIsDefaultMode())
    }

    /**
     * 检测是否是默认模式
     */
    @JvmStatic
    fun checkIsDefaultMode():Boolean{
        return SkinCompatResources.getInstance().isDefaultSkin
    }
}