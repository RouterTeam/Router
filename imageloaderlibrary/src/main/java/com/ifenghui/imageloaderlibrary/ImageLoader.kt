package com.ifenghui.imageloaderlibrary
import android.widget.ImageView
import com.ifenghui.imageloaderlibrary.progress.OnProgressListener

/**
 * 图片处理
 */
interface ImageLoader {
    /**
     * 显示图片
     */
    fun <CONTEXT,RES>displayImage(context: CONTEXT, url: RES?, imageView: ImageView?,cornerRadiousDp:Int,imageListener:ImageLoaderListener?, onProgressListener: OnProgressListener?)

    /**
     * 显示头像圆图
     */
    fun <CONTEXT,RES>displayCircleImage(context: CONTEXT, url: RES?, imageView: ImageView?,cornerRadiousDp:Int,imageListener:ImageLoaderListener?,onProgressListener: OnProgressListener?)

    /**
     * 获取图片资源
     */
    fun <CONTEXT,RES>getImageUrlBitmap(context: CONTEXT, url: RES?,imageListener:ImageLoaderListener?,onProgressListener: OnProgressListener?)
}