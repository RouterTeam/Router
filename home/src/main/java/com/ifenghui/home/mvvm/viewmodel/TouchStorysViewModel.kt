package com.ifenghui.home.mvvm.viewmodel

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Application
import android.view.View
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.ifenghui.apilibrary.api.entity.Story
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel
import com.ifenghui.commonlibrary.utils.ViewUtils
import com.ifenghui.commonlibrary.utils.VoicePromptUtils
import com.ifenghui.home.R
import com.ifenghui.home.mvvm.model.TouchStorysModel
import com.ifenghui.home.widget.TouchContentView

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
    var showRecommendStorys: MutableLiveData<Boolean> = MutableLiveData()
    var showRecommendAlpha: MutableLiveData<Float> = MutableLiveData()
    var recommendTopLeft: MutableLiveData<Story> = MutableLiveData()
    var recommendTopRight: MutableLiveData<Story> = MutableLiveData()
    var recommendMiddleLeft: MutableLiveData<Story> = MutableLiveData()
    var recommendMiddleRight: MutableLiveData<Story> = MutableLiveData()
    var recommendBottomLeft: MutableLiveData<Story> = MutableLiveData()
    var recommendBottomRight: MutableLiveData<Story> = MutableLiveData()
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
    private var planTransYStart = 0f
    private var planRotationMax = -20f
    private var planReverseMax = 0f
    private var shipScrolledSum = 0

    private var isNeedPlayEntrace = true//标示是否需要播放入场动画
    private var isPlayingAnim = true//标示动画是否播放中

    private var currentSelectStory: TouchContentView? = null
    private var listdata: ArrayList<Story>? = null
    private var voiceTipResource = R.raw.moyimo_first_arrival

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
        showRecommendAlpha.value = 0f
        shipResource.value = R.drawable.moyimo_zhendonghua
        showRecommendStorys.value = false
        initAnims()
        startShipEntranceAnim()
    }

    /**
     * 初始化 相关动画
     */
    private fun initAnims() {
        //背景循环动画
        shipBgAnim = ValueAnimator.ofInt(0, -disdance)
        shipBgAnim?.duration = 1000
        shipBgAnim?.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            val dy: Int = value - shipScrolledSum
            shipScrolledSum = value
            scrollBy.value = dy
        }
        shipBgAnim?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                showRecommendAlpha.value = 1f
                setRecommendsData()

                isPlayingAnim = false
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
                if (isNeedPlayEntrace) {
                    shipReversenAnim?.setFloatValues(planTransYMin, planTransYStart)
                    VoicePromptUtils.getInstance().playVoiceTips(getApplication(), R.raw.moyimo_first_enter, null)
                    isPlayingAnim = false
                } else
                    shipReversenAnim?.setFloatValues(planTransYMax, planReverseMax)
                shipReversenAnim?.start()
                isNeedPlayEntrace = false
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
                shipResource.value = R.mipmap.moyimo_frame6
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
            shipTanslationAnim?.setFloatValues(-planTransYMin * 3, planTransYMin)
            shipTanslationAnim?.start()
        } else {
            planTrany.value = 0f
            shipScale.value = 0.6f
            startShipFlyAnim()
        }
    }

    /**
     * 处理摸一摸
     */
    fun dealTouchingClick() {
        if (isPlayingAnim) return
        isPlayingAnim = true
        currentSelectStory=null
        if (shipScale.value == 1.0f) {//处理需要播放入场动画的点击
            playPrepareFlyingAnim()
            return
        }
        if (shipScrolledSum == 0) { //不需要复原直接执行动画
            startShipFlyAnim()
            return
        }
        showRecommendStorys.value = false// 推荐故事隐藏
        VoicePromptUtils.getInstance().pauseMedia()
        getStoryTips()
        playRecoverAnim(false)
    }

    /**
     * 播放准备动画
     */
    private fun playPrepareFlyingAnim() {
        VoicePromptUtils.getInstance().playVoiceTips(getApplication(), R.raw.moyimo_first_touch, null)
        shipFrame.value = true
        alphaAnim?.startDelay = 4000
        alphaAnim?.start()
    }

    /**
     * 播放复原动画
     */
    private fun playRecoverAnim(isNeedScale: Boolean) {
        shipScrolledSum = 0
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
        shipScrolledSum = 0
        shipBgAnim?.start()
        shipTanslationAnim?.setFloatValues(0f, planTransYMax)
        shipTanslationAnim?.start()
        shipRotationAnim?.start()
        startTransAnim?.start()
        VoicePromptUtils.getInstance().playVoiceTips(getApplication(), R.raw.moyimio_xiuxiuxiu, null)
    }

    /**
     * 处理推荐故事点击事件
     */
    fun touchViewClick(view: View) {
        if (isPlayingAnim) return
        view.let {
            it as TouchContentView
            if (it.scaleX == 1f||it.scaleX==-1f){
                if (currentSelectStory?.id == it.id) {//点击的和当前的是同一个

                } else {
                    VoicePromptUtils.getInstance().playVoiceTips(getApplication(), R.raw.common_btn,null)
                    currentSelectStory?.recoverDefaultAnim()
                    currentSelectStory = it
                    it.playReverseAnim()
                }
            }
        }
    }

    /**
     * 更新数据
     */
    fun setListData(list: ArrayList<Story>?) {
        listdata = list
    }

    /**
     * 填充推荐书籍
     */
    private fun setRecommendsData() {
        val size = listdata?.size ?: 0
        if (listdata == null || (size < 6)) {//数据异常
            return
        }
        val index = (Math.random() * (size - 1)).toInt()
        recommendTopLeft.value = listdata?.get(index)
        recommendTopRight.value = listdata?.get((index + 1) % (size - 1))
        recommendMiddleLeft.value = listdata?.get((index + 2) % (size - 1))
        recommendMiddleRight.value = listdata?.get((index + 3) % (size - 1))
        recommendBottomLeft.value = listdata?.get((index + 4) % (size - 1))
        recommendBottomRight.value = listdata?.get((index + 5) % (size - 1))
        showRecommendStorys.value = true
        VoicePromptUtils.getInstance().playVoiceTips(getApplication(), voiceTipResource, null)
    }

    /**
     * 播放故事介绍提示语音
     */
    private fun getStoryTips() {
        val index = 1 + (Math.random() * (6)).toInt()
        voiceTipResource = when (index) {
            1 -> R.raw.moyimo_arrival_1
            2 -> R.raw.moyimo_arrival_2
            3 -> R.raw.moyimo_arrival_3
            4 -> R.raw.moyimo_arrival_4
            5 -> R.raw.moyimo_arrival_5
            6 -> R.raw.moyimo_arrival_6
            7 -> R.raw.moyimo_arrival_7
            else -> R.raw.moyimo_arrival_1
        }
    }

    /**
     * 获取焦点
     */
    override fun onResume() {
        super.onResume()
        VoicePromptUtils.getInstance().resetMediaVolume(1.0f)
    }

    /**
     * 失去焦点
     */
    override fun onPause() {
        super.onPause()
        VoicePromptUtils.getInstance().resetMediaVolume(0f)
    }

    /**
     * 释放资源
     */
    override fun onCleared() {
        super.onCleared()
        try {
            VoicePromptUtils.getInstance().releasePrompt()
            shipBgAnim?.end()
            shipBgAnim = null
            shipTanslationAnim?.end()
            shipTanslationAnim = null
            shipRecoverTransAnim?.end()
            shipRecoverTransAnim = null
            shipRotationAnim?.end()
            shipRotationAnim = null
            shipRecoverRotationAnim?.end()
            shipRecoverRotationAnim = null
            shipReversenAnim?.end()
            shipReversenAnim = null
            shipScalenAnim?.end()
            shipScalenAnim = null
            startTransAnim?.end()
            startTransAnim = null
            alphaAnim?.end()
            alphaAnim = null
        } catch (e: Exception) {
        }
    }
}