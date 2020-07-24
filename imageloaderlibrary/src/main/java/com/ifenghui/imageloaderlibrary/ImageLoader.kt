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
     * 添加bitmap 加载监听
     */
    fun <CONTEXT,RES>displayWithBitmapAndLoaderListener(context: CONTEXT, url: RES?,imageListener:ImageLoaderListener<Bitmap>?):ImageLoader?

    /**
     * 指定大小
     */
    fun resetTargetSize(width: Int, height: Int):ImageLoader?

    /**
     * 设置显示view
     */
    fun intoTargetView(imageView: ImageView)

    /**
     * 显示图片
     */
//    fun <CONTEXT,RES>displayImage(context: CONTEXT, url: RES?, imageView: ImageView?,cornerRadiousDp:Int,imageListener:ImageLoaderListener?, onProgressListener: OnProgressListener?)

    /**
     * 高斯模糊显示图片
     */
//    fun <CONTEXT,RES>displayImageWithBlur(context: CONTEXT, url: RES?, imageView: ImageView?,cornerRadiousDp:Int,blurRadiousDp:Int,imageListener:ImageLoaderListener?, onProgressListener: OnProgressListener?)

    /**
     * 显示头像圆图
     */
//    fun <CONTEXT,RES>displayCircleImage(context: CONTEXT, url: RES?, imageView: ImageView?,imageListener:ImageLoaderListener?,onProgressListener: OnProgressListener?)

    /**
     * 高斯模糊显示头像圆图
     */
//    fun <CONTEXT,RES>displayCircleImageWithBlur(context: CONTEXT, url: RES?, imageView: ImageView?,blurRadiousDp:Int,imageListener:ImageLoaderListener?,onProgressListener: OnProgressListener?)

    /**
     * 获取图片资源
     */
//    fun <CONTEXT,RES>getImageUrlBitmap(context: CONTEXT, url: RES?,imageListener:ImageLoaderListener?,onProgressListener: OnProgressListener?)
}