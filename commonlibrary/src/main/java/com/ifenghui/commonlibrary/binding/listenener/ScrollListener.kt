package com.ifenghui.commonlibrary.binding.listenener

import androidx.recyclerview.widget.RecyclerView

interface ScrollListener {
    fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
}