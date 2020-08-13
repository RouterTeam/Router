package com.ifenghui.home.binding

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ifenghui.home.R

object ViewAnimBindingAdapter {

    /**
     * 绑定移动事件
     */
    @JvmStatic
    @BindingAdapter("bind_transylistener")
    fun bindTransyListener(view: View, translationY: Float) {
        view.translationY=translationY
    }

    /**
     * 绑定旋转事件
     */
    @JvmStatic
    @BindingAdapter("bind_rotationlistener")
    fun bindRotationListener(view: View, rotation: Float) {
        view.rotation=rotation
    }

    /**
     * 绑定流星移动事件
     */
    @JvmStatic
    @BindingAdapter("bind_startlistener")
    fun bindStartListener(view: View, translation: Float) {
        view.translationY=translation
        view.translationX=translation
    }

    /**
     * 绑定帧动画事件
     */
    @JvmStatic
    @BindingAdapter("bind_resourcelistener","bind_framelistener")
    fun bindFrameListener(view: ImageView, resource:Int, startAnim: Boolean) {
        view.setImageResource(resource)
        if (startAnim){
            val drawable: AnimationDrawable = view.drawable as AnimationDrawable
            drawable.start()
        }
    }

    /**
     * 绑定渐隐渐显事件
     */
    @JvmStatic
    @BindingAdapter("bind_alphalistener")
    fun bindAlphaListener(view: View, alpha: Float) {
        view.alpha=alpha
    }

}