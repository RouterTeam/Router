package com.ifenghui.home.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.colin.library.GlideImageLoader
import com.ifenghui.apilibrary.api.entity.Story
import com.ifenghui.commonlibrary.utils.ViewUtils
import com.ifenghui.home.R
import kotlinx.android.synthetic.main.item_touch_recommend_layout.view.*

class TouchContentView : ConstraintLayout {

    private var thirdPlaneMoveAnimator: ObjectAnimator? = null
    private var scaleXAnim: ObjectAnimator? = null
    private var scaleYAnim: ObjectAnimator? = null
    private var list:ArrayList<Int>?=null
    private var touchRoot:View ?= null
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var currentStory:Story?=null

    override fun onFinishInflate() {
        super.onFinishInflate()
        touchRoot=LayoutInflater.from(context).inflate(R.layout.item_touch_recommend_layout,this)
        touchRoot?.iv_play?.let { GlideImageLoader.getInstance().displayWithDrawable(context,R.mipmap.moyimo_yinzhu)?.intoTargetView(it) }
        list=ArrayList()
        list?.add(100)
        list?.add(130)
        list?.add(150)
        list?.add(170)
        list?.add(340)
        list?.add(250)
        list?.add(160)
        list?.add(370)
        list?.add(380)
        list?.add(290)
        list?.add(300)
        list?.add(210)
        list?.add(220)
        initAnim()
        playReversenAnim()

    }

    private fun initAnim() {
        scaleXAnim = ObjectAnimator.ofFloat(this, "scaleX",0f, 1.3f,0.9f, 1.0f)
        scaleYAnim = ObjectAnimator.ofFloat(this, "scaleY",0f, 1.3f,0.9f, 1.0f)
        scaleXAnim?.duration = 380
        scaleYAnim?.duration = 380
    }

    /**
     * 加载图片资源
     */
    fun loadImageSource(story: Story?){
        story?.let {
            touchRoot?.iv_pre?.let { it1 -> GlideImageLoader.getInstance().displayCircleWithBitmap(context,it.cover)?.resetCrossFadeTime(10)?.resetPlaceHolder(0,0)?.intoTargetView(it1) }
        }
    }

    /**
     * 显示或隐藏动画
     */
    fun playShoworHideAnim(isShow: Boolean) {
        scaleXAnim?.end()
        scaleYAnim?.end()
        touchRoot?.iv_play?.alpha=0f
        if (isShow) {
            scaleXAnim?.setFloatValues(0f, 1.2f, 1.0f)
            scaleYAnim?.setFloatValues(0f, 1.2f, 1.0f)
            thirdPlaneMoveAnimator?.resume()
        } else {
            scaleXAnim?.setFloatValues(scaleX, 0f)
            scaleYAnim?.setFloatValues(scaleY, 0f)
        }
        var index= getRandomIndex()
        scaleYAnim?.startDelay=list!![index].toLong()
        scaleXAnim?.startDelay=list!![index].toLong()
        scaleXAnim?.duration = 380
        scaleXAnim?.start()
        scaleYAnim?.start()
    }

    /**
     * 播放翻转动画
     */
    fun playReverseAnim() {
        thirdPlaneMoveAnimator?.pause()
        scaleXAnim?.setFloatValues(scaleX,0f, scaleX)
        scaleXAnim?.duration = 1500
        postDelayed({
            touchRoot?.iv_play?.alpha=1f
        },1000)
        scaleXAnim?.start()
    }

    /**
     * 恢复默认动画
     */
    fun recoverDefaultAnim(){
        touchRoot?.iv_play?.alpha=0f
        thirdPlaneMoveAnimator?.resume()
    }

    /**
     * 播放循环动画
     */
    fun playReversenAnim() {
        if (thirdPlaneMoveAnimator == null) {
            thirdPlaneMoveAnimator = ObjectAnimator.ofFloat(this, "translationY", this.translationY, this.translationY + ViewUtils.dip2px(context, 30f))
        }
        thirdPlaneMoveAnimator?.let {
            it.duration = 1000
            it.repeatCount = 1000
            it.repeatMode = ValueAnimator.REVERSE
            it.startDelay= list!![getRandomIndex()].toLong()
            it.start()
        }

    }

    /**
     * 获取随机数
     */
    private fun getRandomIndex():Int{
        return (Math.random()*12).toInt()
    }
}