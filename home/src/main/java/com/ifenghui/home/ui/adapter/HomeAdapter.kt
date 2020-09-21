package com.ifenghui.home.ui.adapter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.ifenghui.apilibrary.api.entity.*
import com.ifenghui.commonlibrary.application.Constance
import com.ifenghui.commonlibrary.base.event.BaseEvent
import com.ifenghui.commonlibrary.base.ui.adapter.BaseBindAdapter
import com.ifenghui.commonlibrary.provider.ProviderHelper
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
                item as HomeTitle?
                binding.homeTitle = item
                binding.tvMore?.setOnClickListener {
                    //let ,with ,run 函数是以闭包形式返回最后一行代码的值
                    //let可以省去使用前每次都判空，函数内部it指代当前对象
//                    item?.let {
//                        it as HomeTitle
//                        ProviderHelper.startAct(Constance.LOGIN_FRAGMENT_FLAG,getmContext(),"group_more/${it.targetValue}/${it?.name}",null)
//                    }
                    //使用with函数需要传入对象作为参数，需要判空，函数内部this指代传入的对象
//                    with(item){
//                        ProviderHelper.startAct(Constance.LOGIN_FRAGMENT_FLAG,getmContext(),"group_more/${this?.targetValue}/${this?.name}",null)
//                    }
                    //run 是let和with的结合  run可以省去使用前每次都判空， 函数内部this指代当前对象
//                    item?.run {
//                        this as HomeTitle
//                        ProviderHelper.startAct(Constance.LOGIN_FRAGMENT_FLAG,getmContext(),"group_more/${targetValue}/${name}",null)
//                    }

                    //apply函数的返回的是传入对象的本身,使用和run类似
//                    var result=item?.apply {
//                        this as HomeTitle
//                        ProviderHelper.startAct(Constance.LOGIN_FRAGMENT_FLAG,getmContext(),"group_more/${targetValue}/${name}",null)
//                    }

                    //适用于let函数的任何场景，also函数和let很像，只是唯一的不同点就是let函数最后的返回值是最后一行的返回值而also函数的返回值是返回当前的这个对象。一般可用于多个扩展函数链式调用
                    var result=item?.also {
                        it as HomeTitle
                        ProviderHelper.startAct(Constance.LOGIN_FRAGMENT_FLAG,getmContext(),"group_more/${it.targetValue}/${it?.name}",null)
                    }

                }
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