package com.ifenghui.commonlibrary.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ifenghui.commonlibrary.binding.listenener.ScrollListener

object RecyclerViewBindingAdapter {

    /**
     * 绑定滚动事件
     */
    @JvmStatic
    @BindingAdapter("app:bind_scrollchangelistener")
    fun bindRecyclerViewScrollListener(recyclerView: RecyclerView,scrollListener:ScrollListener){
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollListener?.onScrolled(recyclerView,dx,dy)
            }
        })
    }
}