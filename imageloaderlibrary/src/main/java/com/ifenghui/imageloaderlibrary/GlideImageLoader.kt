package com.ifenghui.imageloaderlibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.ifenghui.imageloaderlibrary.progress.OnProgressListener
import com.ifenghui.imageloaderlibrary.progress.ProgressManager
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * Glide图片处理类
 */
@Suppress("DEPRECATION")
class GlideImageLoader : ImageLoader {
    private val defaultViewWidth = 423
    private val defaultViewHeight = 537

    //Glide实现渐入动画效果
    private val crossFade = DrawableTransitionOptions.withCrossFade(500)

    private val defaultOptions: RequestOptions = RequestOptions().placeholder(R.mipmap.item_default).error(R.mipmap.item_default).diskCacheStrategy(DiskCacheStrategy.DATA).dontAnimate()
    private val centerCropOptions: RequestOptions = RequestOptions().centerCrop().placeholder(R.mipmap.item_default).error(R.mipmap.item_default).diskCacheStrategy(DiskCacheStrategy.DATA).dontAnimate()
    private val centerInsideOptions: RequestOptions = RequestOptions().centerInside().placeholder(R.mipmap.item_default).error(R.mipmap.item_default).diskCacheStrategy(DiskCacheStrategy.DATA).dontAnimate()
    private val fitCenterOptions: RequestOptions = RequestOptions().fitCenter().placeholder(R.mipmap.item_default).error(R.mipmap.item_default).diskCacheStrategy(DiskCacheStrategy.DATA).dontAnimate()
    private val circleCropOptions: RequestOptions = RequestOptions().circleCrop().placeholder(R.mipmap.item_default).error(R.mipmap.item_default).diskCacheStrategy(DiskCacheStrategy.DATA).dontAnimate()

    /**
     * 单例方式获取
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: GlideImageLoader? = null

        @JvmStatic
        fun getInstance(): GlideImageLoader {
            if (INSTANCE == null) {
                synchronized(GlideImageLoader::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = GlideImageLoader()
                    }
                }
            }
            return INSTANCE ?: GlideImageLoader()
        }
    }

    /**
     * 获取GlideRequest
     */
    private fun <CONTEXT, RES> getGlideRequest(context: CONTEXT, url: RES?): GlideRequest<Drawable>? {
        var with = when (context) {
            is Fragment -> {
                GlideApp.with(context)
            }
            is Activity -> {
                GlideApp.with(context)
            }
            is View -> {
                GlideApp.with(context)
            }
            is Context -> {
                GlideApp.with(context)
            }
            else -> null
        }
        return with?.asDrawable()?.load(url)?.thumbnail(0.05f)?.transition(crossFade)
//        return with?.asDrawable()?.load(url)?.transition(crossFade)
    }

    /**
     * 设置展示方式
     */
    private fun resetDisplay(scaleType: ImageView.ScaleType?, needCircleCrop: Boolean, requestBuilder: GlideRequest<Drawable>?): GlideRequest<Drawable>? {
        val option =
            if (needCircleCrop)
                circleCropOptions
            else
                when (scaleType) {
                    ImageView.ScaleType.CENTER_CROP -> {
                        centerCropOptions
                    }
                    ImageView.ScaleType.CENTER_INSIDE -> {
                        centerInsideOptions
                    }
                    ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_END -> {
                        fitCenterOptions
                    }
                    else -> {
                        defaultOptions
                    }
                }
        return requestBuilder?.apply(option)
    }

    /**
     * 添加圆角
     */
    private fun resetCorner(cornerDp: Int, requestBuilder: GlideRequest<Drawable>?): GlideRequest<Drawable>? {
        if (cornerDp != 0)//添加圆角
            return requestBuilder?.transform(RoundedCornersTransformation(cornerDp, 0))
        return requestBuilder
    }

    /**
     * 指定图片大小
     */
    private fun resetWidthAndHeight(width: Int, height: Int, requestBuilder: GlideRequest<Drawable>?): GlideRequest<Drawable>? {
//        Log.e("--------","width="+width+"---height="+height)
        return requestBuilder?.override(if (width == 0) defaultViewWidth else width, if (height == 0) defaultViewHeight else height)
    }

    /**
     * 添加监听
     */
    private fun <RES> addGlideListeners(url: RES?, imageListener: ImageLoaderListener?, onProgressListener: OnProgressListener?, requestBuilder: GlideRequest<Drawable>?): GlideRequest<Drawable>? {
        if (imageListener != null || onProgressListener != null) {
            if (onProgressListener != null && url is String)
                ProgressManager.addListener(url, onProgressListener)
            //监听图片加载
            return requestBuilder?.listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    imageListener?.onRequestFailed()
                    if (onProgressListener != null && url is String)
                        removeProcessListener(url)
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    imageListener?.onRequestSuccess(resource)
                    if (onProgressListener != null && url is String)
                        removeProcessListener(url)
                    return false
                }
            })
        }
        return requestBuilder
    }

    /**
     * 清楚下载监听
     */
    private fun removeProcessListener(url: String) {
        val onProgressListener = ProgressManager.getProgressListener(url)
        if (onProgressListener != null) {
            onProgressListener.onProgress(true, 100, 0, 0)
            ProgressManager.removeListener(url)
        }
    }


    /**
     * 加载图片
     */
    private fun <CONTEXT, RES> displayDefaultImage(context: CONTEXT, url: RES?, imageView: ImageView?, needCircleCrop: Boolean, cornerDp: Int, imageListener: ImageLoaderListener?, onProgressListener: OnProgressListener?) {
        try {
            if (imageView != null) {
                //添加监听
                var requestBuilder = addGlideListeners(url, imageListener, onProgressListener, getGlideRequest(context, url))
                //设置展示方式
                requestBuilder = resetDisplay(imageView.scaleType, needCircleCrop, requestBuilder)
                //添加圆角
                requestBuilder = resetCorner(cornerDp, requestBuilder)
                //指定图片大小
                requestBuilder = resetWidthAndHeight(imageView.width, imageView.height, requestBuilder)
                requestBuilder?.into(imageView)
            }
        } catch (e: Exception) {
        }
    }


    /**
     * 普通方式加载
     */
    override fun <CONTEXT, RES> displayImage(context: CONTEXT, url: RES?, imageView: ImageView?, cornerRadiousDp: Int, imageListener: ImageLoaderListener?, onProgressListener: OnProgressListener?) {
        displayDefaultImage(context, url, imageView, false, cornerRadiousDp, imageListener, onProgressListener)
    }

    /**
     * CircleCrop方式加载
     */
    override fun <CONTEXT, RES> displayCircleImage(context: CONTEXT, url: RES?, imageView: ImageView?, cornerRadiousDp: Int, imageListener: ImageLoaderListener?, onProgressListener: OnProgressListener?) {
        displayDefaultImage(context, url, imageView, true, cornerRadiousDp, imageListener, onProgressListener)
    }

    /**
     * 获取图片bitmap资源
     */
    override fun <CONTEXT, RES> getImageUrlBitmap(context: CONTEXT, url: RES?, imageListener: ImageLoaderListener?, onProgressListener: OnProgressListener?) {
        if (onProgressListener != null && url is String)
            ProgressManager.addListener(url, onProgressListener)
        getGlideRequest(context, url)?.diskCacheStrategy(DiskCacheStrategy.RESOURCE)?.into(object : SimpleTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                imageListener?.onRequestSuccess(resource)
                if (onProgressListener != null && url is String)
                    removeProcessListener(url)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                imageListener?.onRequestFailed()
                if (onProgressListener != null && url is String)
                    removeProcessListener(url)
            }
        })
    }
}