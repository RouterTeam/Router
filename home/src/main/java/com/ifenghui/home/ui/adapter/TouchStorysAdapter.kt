package com.ifenghui.home.ui.adapter

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.ifenghui.commonlibrary.base.ui.adapter.BaseBindAdapter
import com.ifenghui.home.R
import com.ifenghui.home.databinding.ItemTouchStorysLayoutBinding

class TouchStorysAdapter(context: Context?)  : BaseBindAdapter<Int, ViewDataBinding>(context) {
    var repeatSize=0
    init {
        datas=ArrayList()
        datas.add(R.mipmap.moyimo_bg1)
        datas.add(R.mipmap.moyimo_bg2)
        datas.add(R.mipmap.moyimo_bg3)
        datas.add(R.mipmap.moyimo_bg4)
        datas.add(R.mipmap.moyimo_bg5)
        datas.add(R.mipmap.moyimo_bg6)
        datas.add(R.mipmap.moyimo_bg7)
        datas.add(R.mipmap.moyimo_bg8)
        datas.add(R.mipmap.moyimo_bg9)
        datas.add(R.mipmap.moyimo_bg10)

        repeatSize=datas.size-2
    }
    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun getItem(i: Int): Int {
        return when(i){
            Int.MAX_VALUE-1 -> datas[0]
            Int.MAX_VALUE-2 -> datas[1]
            else ->{
                val sum = Int.MAX_VALUE - i - 3
                datas[(sum % repeatSize)+2]
            }
        }
    }

    override fun getItemLayout(viewType: Int): Int {
        return R.layout.item_touch_storys_layout
    }

    override fun onBindItem(binding: ViewDataBinding?, item: Int?, position: Int) {
        binding as ItemTouchStorysLayoutBinding
        item?.let { binding?.ivConver?.setImageResource(it) }
    }
}