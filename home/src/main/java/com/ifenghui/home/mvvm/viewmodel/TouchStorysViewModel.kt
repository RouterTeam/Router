package com.ifenghui.home.mvvm.viewmodel

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.commonlibrary.utils.ViewUtils
import com.ifenghui.home.R
import com.ifenghui.home.mvvm.model.TouchStorysModel

class TouchStorysViewModel : BaseViewModel<TouchStorysModel> {
    private var disdance = 0
    var scrollBy: MutableLiveData<Int> = MutableLiveData()
    var planTrany: MutableLiveData<Float> = MutableLiveData()
    var planRotation: MutableLiveData<Float> = MutableLiveData()
    var startTrans: MutableLiveData<Float> = MutableLiveData()
    var shipScale: MutableLiveData<Float> = MutableLiveData()
    var shipFrame: MutableLiveData<Boolean> = MutableLiveData()
    var shipResource: MutableLiveData<Int> = MutableLiveData()
    var shipAlpha: MutableLiveData<Float> = MutableLiveData()
    private var shipBgAnim: ValueAnimator? = null
    private var shipTanslationAnim: ValueAnimator? = null
    private var shipRecoverTransAnim: ValueAnimator? = null
    private var shipRotationAnim: ValueAnimator? = null
    private var shipRecoverRotationAnim: ValueAnimator? = null
    private var shipReversenAnim: ValueAnimator? = null
    private var shipScalenAnim: ValueAnimator? = null
    private var startTransAnim: ValueAnimator? = null
    private var alphaAnim: ValueAnimator? = null
    private var planTransYMax = 0f
    private var planTransYMin = 0f
    private var planTransYStart=0f
    private var planRotationMax = -20f
    private var planReverseMax = 0f

    private var isNeedPlayEntrace = true//标示是否需要播放入场动画
    private var isPlayingAnim = false//标示动画是否播放中

    constructor(@NonNull application: Application, model: TouchStorysModel) : super(application, model) {
        disdance = (ViewUtils.getScreenWidth(application) * 667f / 750f * 8).toInt()
        planTransYMax = -ViewUtils.dip2px(application, 180f).toFloat()
        planReverseMax = -ViewUtils.dip2px(application, 130f).toFloat()
        planTransYMin = -ViewUtils.dip2px(application, 130f) * 1.0f
        planTransYStart = -ViewUtils.dip2px(application, 80f) * 1.0f
        scrollBy.value = 0
        planTrany.value = planTransYMin
        planRotation.value = 0f
        startTrans.value = 0f
        shipScale.value = 1.0f
        shipAlpha.value = 0f
        shipResource.value= R.drawable.moyimo_zhendonghua
        initAnims()
        startShipEntranceAnim()
    }

    var sum = 0

    /**
     * 初始化 相关动画
     */
    private fun initAnims() {
        //背景循环动画
        shipBgAnim = ValueAnimator.ofInt(0, -disdance)
        shipBgAnim?.duration = 1000
        shipBgAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val dy: Int = value - sum
            sum = value
            scrollBy.value = dy
        }
        shipBgAnim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                isPlayingAnim=false
            }
        })

        //流星动画
        startTransAnim = ObjectAnimator.ofFloat(0f, ViewUtils.getScreenWidth(getApplication()) * 2f)
        startTransAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            startTrans.value = value
        }
        startTransAnim?.repeatMode = ValueAnimator.RESTART
        startTransAnim?.repeatCount = 1
        startTransAnim?.duration = 800


        //飞船移动动画
        shipTanslationAnim = ObjectAnimator.ofFloat(0f, planTransYMax)
        shipTanslationAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            planTrany.value = value
        }
        shipTanslationAnim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                shipReversenAnim?.setFloatValues(planTransYMax, planReverseMax)
                shipReversenAnim?.start()
            }
        })
        shipTanslationAnim?.duration = 1000

        //飞船旋转动画
        shipRotationAnim = ObjectAnimator.ofFloat(0f, planRotationMax)
        shipRotationAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            planRotation.value = value
        }
        shipRotationAnim?.duration = 1000

        //飞船循环动画
        shipReversenAnim = ObjectAnimator.ofFloat()
        shipReversenAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            planTrany.value = value
        }
        shipReversenAnim?.duration = 1000
        shipReversenAnim?.repeatCount = -1
        shipReversenAnim?.repeatMode = ValueAnimator.REVERSE

        //飞船旋转复原动画
        shipRecoverRotationAnim = ObjectAnimator.ofFloat(planRotationMax, 0f)
        shipRecoverRotationAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            planRotation.value = value
        }
        shipRecoverRotationAnim?.duration = 600

        //飞船缩放动画
        shipScalenAnim = ObjectAnimator.ofFloat(1.0f, 0.6f)
        shipScalenAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            shipScale.value = value
        }
        shipScalenAnim?.duration = 600

        //飞船移动复原动画
        shipRecoverTransAnim = ObjectAnimator.ofFloat()
        shipRecoverTransAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            planTrany.value = value
        }
        shipRecoverTransAnim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                startShipFlyAnim()
            }
        })
        shipRecoverTransAnim?.duration = 600

        //渐隐渐显动效
        alphaAnim = ObjectAnimator.ofFloat(0f, 1f)
        alphaAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            shipAlpha.value = value
        }
        alphaAnim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                shipAlpha.value = 0f
                shipFrame.value = false
                shipResource.value=R.mipmap.moyimo_frame6
                playRecoverAnim(true)
            }
        })
        alphaAnim?.duration = 1000
    }

    /**
     * 执行飞船入场动画
     */
    private fun startShipEntranceAnim() {
        if (isNeedPlayEntrace) {
            shipReversenAnim?.setFloatValues(planTransYMin, planTransYStart)
            shipReversenAnim?.start()
        } else {
            planTrany.value = 0f
            shipScale.value = 0.6f
        }
    }

    /**
     * 处理摸一摸
     */
    fun dealTouchingClick() {
        if (isPlayingAnim) return
        isPlayingAnim = true
        if (shipScale.value == 1.0f) {//处理需要播放入场动画的点击
            playPrepareFlyingAnim()
            return
        }
        if (sum == 0) { //不需要复原直接执行动画
            startShipFlyAnim()
            return
        }
        playRecoverAnim(false)
    }

    /**
     * 播放准备动画
     */
    private fun playPrepareFlyingAnim() {
        shipFrame.value = true
        alphaAnim?.startDelay = 4000
        alphaAnim?.start()
    }

    /**
     * 播放复原动画
     */
    private fun playRecoverAnim(isNeedScale: Boolean) {
        sum = 0
        val recoverStart = planTrany.value ?: planReverseMax
        shipReversenAnim?.end()
        shipRecoverTransAnim?.setFloatValues(recoverStart, 0f)
        shipRecoverTransAnim?.start()
        if (isNeedScale)
            shipScalenAnim?.start()
        else
            shipRecoverRotationAnim?.start()
    }

    /**
     * 执行飞船飞行动画
     */
    private fun startShipFlyAnim() {
        sum = 0
        shipBgAnim?.start()
        shipTanslationAnim?.start()
        shipRotationAnim?.start()
        startTransAnim?.start()
    }
}