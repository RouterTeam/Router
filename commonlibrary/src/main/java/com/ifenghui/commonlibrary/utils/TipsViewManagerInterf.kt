package com.ifenghui.commonlibrary.utils

import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.TextView
import com.ifenghui.commonlibrary.R
import com.ifenghui.commonlibrary.view.ErrorStatusView
import com.ifenghui.commonlibrary.view.LoadingView
import com.ifenghui.commonlibrary.view.NoDataView
import com.ifenghui.commonlibrary.view.NoNetView

interface TipsViewManagerInterf {
    //容器根布局
    var mRootView: View?

    //空数据提示页面
    var mNoDataView: NoDataView?

    //空数据提示页面
    var mNoNetView: NoNetView?

    //空数据提示页面
    var mErrorStatusView: ErrorStatusView?

    //数据加载提示页面
    var mLoadingView: LoadingView?

    /**
     * 初始化rootview
     */
    fun initRootView(rootView: View?) {
        try {
            mRootView = rootView
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * 初始化头部导航栏信息
     */
    fun initToolBar() {
        try {
            if (enableToolbar() || enableTopNav())
                mRootView?.fitsSystemWindows = true
            if (enableToolbar()) {
                val mToolbarViewStub =
                    mRootView?.findViewById<ViewStub>(if (isFragmentLayout()) R.id.fragment_view_stub_toolbar else R.id.view_stub_toolbar)
                mToolbarViewStub?.layoutResource = onBindToolbarLayout()
                mToolbarViewStub?.inflate()
                val backView = mRootView?.findViewById<View>(R.id.iv_toolbar_back)
                if (backView != null) {
                    RxUtils.rxClick(backView, object : RxUtils.OnClickInterf {
                        override fun onViewClick(view: View, data: Any?) {
                            finishActivity()
                        }
                    }, null)
                }

            }
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     *resetToolBar标题
     */
    fun resetToolBarTitle(toolBarTitle: String) {
        try {
            if (enableToolbar()) {
                mRootView?.findViewById<TextView>(R.id.toolbar_title)?.text = toolBarTitle
            }
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * 初始化页面展示view
     */
    fun initContentView(mContentView: View?) {
        try {
            val mContentViewStub =
                mRootView?.findViewById<ViewStub>(if (isFragmentLayout()) R.id.fragment_view_stub_content else R.id.view_stub_content)
            mContentViewStub?.layoutResource = onBindLayout()
            val mContentRootView = mContentViewStub?.inflate() as ViewGroup?
            if (mContentView != null) {
                mContentRootView?.id = android.R.id.content
                mContentView.id = View.NO_ID
            }
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * 空数据提示
     */
    fun showNoDataView() = try {
        resetErrorStatusView(false)
        resetNoNetView(false)
        resetNodataView(true)
        resetLoadingView(false)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 数据异常提示
     */
    fun showErrStatusView() = try {
        resetErrorStatusView(true)
        resetNoNetView(false)
        resetNodataView(false)
        resetLoadingView(false)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 无网络提示
     */
    fun showNetWorkErrView() = try {
        resetErrorStatusView(false)
        resetNoNetView(true)
        resetNodataView(false)
        resetLoadingView(false)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 无网络提示
     */
    fun showLoadingTipsView() = try {
        resetErrorStatusView(false)
        resetNoNetView(false)
        resetNodataView(false)
        resetLoadingView(true)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 隐藏所有提示view
     */
    fun hideAllTipsView() = try {
        resetErrorStatusView(false)
        resetNoNetView(false)
        resetNodataView(false)
        resetLoadingView(false)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * reset空数据状态
     */
    private fun resetNodataView(isShow: Boolean) {
        try {
            if (mNoDataView == null && isShow) {
                val inflateView =
                    mRootView?.findViewById<View>(if (isFragmentLayout()) R.id.fragment_viewStub_nodata else R.id.viewStub_nodata)
                mNoDataView = when (inflateView) {
                    is NoDataView -> {
                        inflateView
                    }
                    is ViewStub -> {
                        inflateView.inflate() as NoDataView?
                    }
                    else -> {
                        null
                    }
                }

            }
            mNoDataView?.visibility = if (isShow) View.VISIBLE else View.GONE
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * rese无网络状态
     */
    private fun resetNoNetView(isShow: Boolean) {
        try {
            if (mNoNetView == null && isShow) {
                val inflateView =
                    mRootView?.findViewById<View>(if (isFragmentLayout()) R.id.fragment_viewStub_nonet else R.id.viewStub_nonet)
                mNoNetView = when (inflateView) {
                    is NoNetView -> {
                        inflateView
                    }
                    is ViewStub -> {
                        inflateView.inflate() as NoNetView?
                    }
                    else -> {
                        null
                    }
                }
                mNoNetView?.setRefreshBtnClickListener(object : Callback<Any>() {
                    override fun call(type: Any?) {
                        hideAllTipsView()
                        onReloadData()
                    }
                })
            }
            mNoNetView?.visibility = if (isShow) View.VISIBLE else View.GONE
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * rese网络异常状态
     */
    private fun resetErrorStatusView(isShow: Boolean) {
        try {
            if (mErrorStatusView == null && isShow) {
                val inflateView =
                    mRootView?.findViewById<View>(if (isFragmentLayout()) R.id.fragment_viewStub_error else R.id.viewStub_error)
                mErrorStatusView = when (inflateView) {
                    is ErrorStatusView -> {
                        inflateView
                    }
                    is ViewStub -> {
                        inflateView.inflate() as ErrorStatusView?
                    }
                    else -> {
                        null
                    }
                }
            }
            mErrorStatusView?.visibility = if (isShow) View.VISIBLE else View.GONE
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * rese数据加载状态
     */
    private fun resetLoadingView(isShow: Boolean) {
        try {
            if (mLoadingView == null && isShow) {
                val inflateView =
                    mRootView?.findViewById<View>(if (isFragmentLayout()) R.id.fragment_viewStub_loading else R.id.viewStub_loading)
                mLoadingView = when (inflateView) {
                    is LoadingView -> {
                        inflateView
                    }
                    is ViewStub -> {
                        inflateView.inflate() as LoadingView?
                    }
                    else -> {
                        null
                    }
                }
            }
            mLoadingView?.visibility = if (isShow) View.VISIBLE else View.GONE
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    fun isFragmentLayout(): Boolean

    /**
     * 页面布局文件
     */
    fun onBindLayout(): Int

    /**
     * 重新加载数据
     */
    fun onReloadData() {}

    /**
     * 是否允许头部导航栏信息
     */
    fun enableToolbar(): Boolean {
        return true
    }

    /**
     * 是否允许顶部状态栏站位
     */
    fun enableTopNav(): Boolean {
        return false
    }

    /**
     * 重置头部导航栏信息
     */
    fun resetToolBar(view: View) {}

    /**
     * 头部导航栏布局
     */
    fun onBindToolbarLayout(): Int {
        return R.layout.common_toolbar
    }

    /**
     * 关闭页面
     */
    fun finishActivity()

    fun onBackPressed()
}