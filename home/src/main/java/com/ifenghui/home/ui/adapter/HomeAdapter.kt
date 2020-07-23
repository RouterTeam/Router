package com.ifenghui.home.ui.adapter

import android.content.Context
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.ifenghui.apilibrary.api.entity.*
import com.ifenghui.commonlibrary.base.event.BaseEvent
import com.ifenghui.commonlibrary.base.ui.adapter.BaseBindAdapter
import com.ifenghui.home.R
import com.ifenghui.home.databinding.*
import org.greenrobot.eventbus.EventBus
import java.util.*

class HomeAdapter(context: Context?) : BaseBindAdapter<Any, ViewDataBinding>(context) {
    private var drawables: ArrayList<Int>?=null
    /**
     * 获取item Type
     */
    override fun getItemType(position: Int): Int {
        val item = datas[position]
        if (item is HomeTitle) return 1
        if (item is Ad) return 2
        if (item is Story) return 3
        if (item is LessonIndex) return 4
        if (item is HomeResult.BannerItemGroup) return 5
        return super.getItemType(position)
    }

    /**
     * 获取item布局资源
     */
    override fun getItemLayout(viewType: Int): Int {
        return when (viewType) {
            1 -> R.layout.item_home_title_layout
            2 -> R.layout.item_home_recomment_layout
            3 -> R.layout.item_story_layout
            4 -> R.layout.item_home_lesson_layout
            5 -> R.layout.item_home_banner_layout
            else -> R.layout.item_home_recomment_layout
        }
    }

    /**
     * 获取 item 的SpanSize
     */
    override fun getCustomerSpanSize(position: Int): Int {
        val item = datas[position]
        if (item is HomeTitle) return 6
        if (item is Ad) return 6
        if (item is Story) return 2
        if (item is LessonIndex) return 3
        if (item is HomeResult.BannerItemGroup) return 6
        return super.getCustomerSpanSize(position)
    }

    /**
     * 绑定数据
     */
    override fun onBindItem(binding: ViewDataBinding?, item: Any?, position: Int) {
        when (binding) {
            is ItemHomeRecommentLayoutBinding -> {
                binding.homeRecomment = item as Ad?
                binding.ivCover.setOnClickListener {
                    EventBus.getDefault().post(BaseEvent<Any>())
                    Toast.makeText(getmContext(), "ddddd", Toast.LENGTH_LONG).show()
                }
            }
            is ItemHomeTitleLayoutBinding -> {
                binding.homeTitle = item as HomeTitle?
            }
            is ItemStoryLayoutBinding -> {
                binding.storyItem = item as Story?
            }

            is ItemHomeLessonLayoutBinding -> {
                binding.homeLesson = item as LessonIndex?
            }

            is ItemHomeBannerLayoutBinding -> {
                if (drawables==null){
                    var drawables = ArrayList<Int>()
                    drawables.add(R.mipmap.banner_bg1)
                    drawables.add(R.mipmap.banner_bg2)
                    drawables.add(R.mipmap.banner_bg3)
                    drawables.add(R.mipmap.banner_bg4)
                    var drawablePre = ArrayList<Int>()
                    drawablePre.add(R.mipmap.banner_pre1)
                    drawablePre.add(R.mipmap.banner_pre2)
                    drawablePre.add(R.mipmap.banner_pre3)
                    drawablePre.add(R.mipmap.banner_pre4)
                    binding.viewpager.setViewPagerData(drawables, drawablePre)
                    binding.homeBanner = item as HomeResult.BannerItemGroup
                }

                binding.viewpager.startImageCycle()
            }
        }
    }
}