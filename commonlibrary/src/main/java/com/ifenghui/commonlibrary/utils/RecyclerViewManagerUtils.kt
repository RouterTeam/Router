package com.ifenghui.commonlibrary.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifenghui.commonlibrary.weiget.WrapHeightGridLayoutManager

class RecyclerViewManagerUtils {
    companion object{
        /**
         * 设置线性样式
         */
        fun setLinearLayoutManager(recyclerView:RecyclerView?,mContext:Context?,isVertical:Boolean){
            val linearLayoutManager = LinearLayoutManager(mContext)
            linearLayoutManager.orientation=if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL
            recyclerView?.layoutManager=linearLayoutManager
        }

        /**
         * 设置网格样式
         */
        fun setGridLayoutManager(recyclerView:RecyclerView?,mContext:Context?,spaceCount:Int){
            val gridLayoutManager = WrapHeightGridLayoutManager(mContext, spaceCount)
            recyclerView?.layoutManager=gridLayoutManager
        }
    }
}