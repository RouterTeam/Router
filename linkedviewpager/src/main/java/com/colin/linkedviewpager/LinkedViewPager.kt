package com.colin.linkedviewpager

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.AttrRes
import com.ifenghui.imageloaderlibrary.GlideImageLoader
import java.util.*

/**
 * Created by Administrator on 2017/3/17 0017.
 */
class LinkedViewPager : FrameLayout {
    /**
     * 上下文
     */
    private var mContext: Context? = null

    /**
     * 展示文字图片
     */
    private var mPager: CycleViewPager? = null
    private var mFramePrePageViews: ArrayList<View>? = null
    private var mPageAdapter: MyPagerAdapter? = null

    /**
     * 展示背景图片
     */
    private var mFramePager: CycleViewPager? = null
    private var mFramePageViews: ArrayList<View>? = null
    private var mFramePageAdapter: MyPagerAdapter? = null

    /**
     * 计时器
     */
    private var timer: Timer? = null
    var currentPosition=100
    companion object{
        var isActivite=true
    }


    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        initView(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        this.mContext = context
        LayoutInflater.from(context).inflate(R.layout.linked_viewpager_layout, this)
        mPager = findViewById<View>(R.id.pager) as CycleViewPager
        mFramePager =
            findViewById<View>(R.id.main_scrolllayout) as CycleViewPager
        mPager?.controlViewPagerSpeed(480, CycleInterpolator(.22f))
//        mPager?.controlViewPagerSpeed(480, AccelerateDecelerateInterpolator())
//        mPager?.controlViewPagerSpeed(480, LinearInterpolator())
        mFramePager?.controlViewPagerSpeed(480, DecelerateInterpolator())
//        mPager?.pageMargin= mContext?.resources?.getDimensionPixelOffset(R.dimen.dp_18)?:10
//        mFramePager?.pageMargin= mContext?.resources?.getDimensionPixelOffset(R.dimen.dp_18)?:10
        bindListener()
    }

    fun setViewPagerData(drawableBgs: List<Int?>, drawablePres: List<Int?>) {
        mFramePager?.setCurrentItem(currentPosition,false)
        mPager?.setCurrentItem(currentPosition,false)
        mFramePageViews = ArrayList()
        mFramePrePageViews = ArrayList()
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val size = drawableBgs.size
        for (i in 0 until size) {
            val frameView =
                inflater.inflate(R.layout.transparent_layer_image, null)
            val image =
                frameView.findViewById<View>(R.id.image) as ImageView
            image.setImageResource(drawableBgs[i]!!)
//            GlideImageLoader.getInstance().displayCircleImage(mContext,"https://storybookoss.ifenghui.com/banner/2019/09/27/nC3JnwBeF5.png",image,0,null,null)
            mFramePageViews?.add(frameView)
            val view = inflater.inflate(R.layout.transparent_layer, null)
            val imagePre = view.findViewById<View>(R.id.iv_pre) as ImageView
            imagePre.setImageResource(drawablePres[i]!!)
            mFramePrePageViews?.add(view)
        }
        mFramePageAdapter = MyPagerAdapter(mFramePageViews)
        mFramePager?.adapter = mFramePageAdapter
        mPageAdapter = MyPagerAdapter(mFramePrePageViews)
        mPager?.adapter = mPageAdapter
        mPager?.setFollowViewPager(mFramePager)
        mFramePager?.setCurrentItem(currentPosition,false)
        mPager?.setCurrentItem(currentPosition,false)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun bindListener() {
        mPager?.setOnPageChangeListener(object :
            ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                currentPosition=position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        mPager?.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                stopScroll()
            } else if (event.action == MotionEvent.ACTION_UP) {
                startScroll()
            }
            false
        }
    }

    /**
     * 开始轮播(手动控制自动轮播与否，便于资源控制)
     */
    fun startImageCycle() {
        startScroll()
    }

    /**
     * 暂停轮播——用于节省资源
     */
    fun pushImageCycle() {
        stopScroll()
    }

    /**
     * 停止滚动
     */
    private fun stopScroll() {
        isActivite=false
        timer?.cancel()
        timer = null
        handler_auto_next?.removeMessages(0)
    }

    /**
     * 开始滚动
     */
    private fun startScroll() {
        isActivite=true
        if (timer == null) {
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handler_auto_next?.sendEmptyMessage(0)
                }
            }, 5000, 5000)
        }
    }

    // 切换当前显示的图片
    @SuppressLint("HandlerLeak")
    private var handler_auto_next: Handler? = object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            if (mPager != null&&isActivite) {
                val index = (mPager?.currentItem ?: 0) + 1
                mPager?.setCurrentItem(index, true)
                mFramePager?.setCurrentItem(index, true)
            }
        }
    }
}