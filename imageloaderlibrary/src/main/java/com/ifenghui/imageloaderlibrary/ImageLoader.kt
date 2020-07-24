package com.ifenghui.imageloaderlibrary
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.ifenghui.imageloaderlibrary.progress.OnProgressListener

/**
 * 图片处理
 */
interface ImageLoader {

    /**
     * drable 的方式获取资源
     */
    fun <CONTEXT,RES>displayWithDrable(context: CONTEXT, url: RES?):ImageLoader?

    /**
     * bitmap 的方式获取资源
     */
    fun <CONTEXT,RES>displayWithBitmap(context: CONTEXT, url: RES?):ImageLoader?

    /**
     * drable 的方式获取资源
     */
    fun <CONTEXT,RES>displayCircleWithDrable(context: CONTEXT, url: RES?):ImageLoader?

    /**
     * bitmap 的方式获取资源
     */
    fun <CONTEXT,RES>displayCircleWithBitmap(context: CONTEXT, url: RES?):ImageLoader?

    /**
     * 高斯模糊的方式加载
     */
    fun <CONTEXT,RES>displayWithBlur(context: CONTEXT, url: RES?,blurRadius:Int):ImageLoader?

    /**
     * 高斯模糊加圆角的方式加载
     */
    fun <CONTEXT,RES>displayWithBlurRound(context: CONTEXT, url: RES?,blurRadius:Int,roundRadius:Int):ImageLoader?

    /**
     * 添加drable 加载监听
     */
    fun <CONTEXT,RES>displayWithDrableAndLoaderListener(context: CONTEXT, url: RES?,imageListener:ImageLoaderListener<Drawable>?):ImageLoader?

    /**
     * 添加drable 加载进度监听
     */
    fun <CONTEXT,RES>displayWithDrableAndProgressListener(context: CONTEXT, url: RES?,onProgressListener: OnProgressListener??):ImageLoader?

    /**
     * 添加bitmap 加载监听
     */
    fun <CONTEXT,RES>displayWithBitmapAndLoaderListener(context: CONTEXT, url: RES?,imageListener:ImageLoaderListener<Bitmap>?):ImageLoader?

    /**
     * 添加bitmap 加载进度监听
     */
    fun <CONTEXT,RES>displayWithBitmapAndProgressListener(context: CONTEXT, url: RES?,onProgressListener: OnProgressListener??):ImageLoader?

    /**
     * 指定大小
     */
    fun resetTargetSize(width: Int, height: Int):ImageLoader?

    /**
     * 重置占位图
     */
    fun resetPlaceHolder(placeholder:Int , error:Int):ImageLoader?

    /**
     * 设置显示view
     */
    fun intoTargetView(imageView: ImageView)

}