package com.colin.shelf.ui.adapter

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.colin.shelf.R
import com.colin.shelf.databinding.ItemShelfSerialLayoutBinding
import com.colin.shelf.databinding.ItemShelfStoryLayoutBinding
import com.ifenghui.apilibrary.api.entity.SerialStory
import com.ifenghui.apilibrary.api.entity.ViewRecord
import com.ifenghui.commonlibrary.base.ui.adapter.BaseBindAdapter

class ShelfAdapter(context: Context?) : BaseBindAdapter<Any, ViewDataBinding>(context) {

    override fun getItemType(position: Int): Int {
        val item = datas[position]
        if (item is ViewRecord) return 1
        if (item is SerialStory) return 2
        return super.getItemType(position)
    }
    override fun getItemLayout(viewType: Int): Int {
        return when (viewType) {
            1 -> R.layout.item_shelf_story_layout
            2 -> R.layout.item_shelf_serial_layout
            else -> R.layout.item_shelf_story_layout
        }
    }

    override fun onBindItem(binding: ViewDataBinding?, item: Any?, position: Int) {
        when (binding) {
            is ItemShelfStoryLayoutBinding ->{
                binding?.viewRecord=item as ViewRecord
            }
            is ItemShelfSerialLayoutBinding ->{
                binding?.serialStory=item as SerialStory
            }
        }
    }
}