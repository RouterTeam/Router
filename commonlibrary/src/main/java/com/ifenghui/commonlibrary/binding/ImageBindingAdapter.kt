package com.ifenghui.commonlibrary.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.colin.library.GlideImageLoader

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageDefaultUrl")
    fun bindDefaultImageUrl(view: ImageView?, imageDefaultUrl: Any?) {
        if (view == null) return
        GlideImageLoader.getInstance().displayWithDrawable(view, imageDefaultUrl)?.resetTargetSize(view.width, view.height)?.intoTargetView(view)
    }

    @JvmStatic
    @BindingAdapter("imageCircleUrl")
    fun bindCircleImageUrl(view: ImageView?, imageCircleUrl: Any?) {
        var imageCircleUrl = imageCircleUrl
        if (view == null) return
        if (imageCircleUrl == null) imageCircleUrl = "http://5b0988e595225.cdn.sohucs.com/images/20171202/a1cc52d5522f48a8a2d6e7426b13f82b.gif"
        GlideImageLoader.getInstance().displayCircleWithDrawable(view, imageCircleUrl)?.intoTargetView(view)
    }

    @JvmStatic
    @BindingAdapter("imageBlurUrl")
    fun bindBlurImageUrl(view: ImageView?, imageCircleUrl: Any?) {
        if (view == null) return
        GlideImageLoader.getInstance().displayWithBlur(view, imageCircleUrl, 25)!!.intoTargetView(view)
    }


    @JvmStatic
    @BindingAdapter("imageDefaultUrl","crossFadeTime")
    fun bindCustomerCrossTime(view: ImageView?, imageDefaultUrl: Any?,crossFadeTime:Int) {
        if (view == null) return

        GlideImageLoader.getInstance().displayWithDrawable(view, imageDefaultUrl)?.resetTargetSize(view.width, view.height)?.resetCrossFadeTime(crossFadeTime)?.intoTargetView(view)
    }

}