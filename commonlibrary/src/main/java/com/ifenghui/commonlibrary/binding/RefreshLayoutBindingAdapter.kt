package com.ifenghui.commonlibrary.binding

import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.colin.smartrefreshlibrary.smartrefresh.layout.SmartRefreshLayout
import com.ifenghui.commonlibrary.binding.LoadMoreMenu.*
import com.ifenghui.commonlibrary.binding.listenener.InverseBindingListener

object RefreshLayoutBindingAdapter {

    /**
     * 绑定刷新事件
     */
    @JvmStatic
    @BindingAdapter("bind_refreshlistener")
    fun bindRefreshListener(
        refreshLayout: SmartRefreshLayout,
        refreshListener: InverseBindingListener
    ) {
        refreshLayout.setOnRefreshListener {
            refreshListener?.onChange()
        }
    }


    /**
     * 绑定加载更多事件
     */
    @JvmStatic
    @BindingAdapter("bind_loadmorelistener")
    fun bindLoadMoreListener(
        refreshLayout: SmartRefreshLayout,
        refreshListener: InverseBindingListener
    ) {
        refreshLayout.setOnLoadMoreListener {
            refreshListener.onChange()
        }
    }


    /**
     * 绑定刷新完成事件
     */
    @JvmStatic
    @BindingAdapter("bind_refreshcompletelistener")
    fun bindRefreshCompleteListener(refreshLayout: SmartRefreshLayout, isComplete: Boolean) {
        if (isComplete)
            refreshLayout.finishRefresh()
    }

    /**
     * 绑定加载更多完成事件
     */
    @JvmStatic
    @BindingAdapter("bind_loadmorecompletelistener")
    fun bindLoadMoreCompleteListener(refreshLayout: SmartRefreshLayout, loadMoreMenu: LoadMoreMenu?) {
        when (loadMoreMenu) {
            HAS_MORE -> refreshLayout.finishLoadMore()
            NO_MORE -> {
                //没有更多数据（上拉加载功能将显示没有更多数据）
                Toast.makeText(
                    refreshLayout.context,
                    "数据全部加载完毕",
                    Toast.LENGTH_SHORT
                ).show()
                refreshLayout.finishLoadMoreWithNoMoreData()//将不会再次触发加载更多事件
            }
            FAILD -> refreshLayout.finishLoadMore(false)
        }
    }
}