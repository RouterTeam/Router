package com.ifenghui.commonlibrary.base.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ifenghui.commonlibrary.R
import com.ifenghui.commonlibrary.base.event.BaseEvent
import com.ifenghui.commonlibrary.base.view.IBaseView
import com.ifenghui.commonlibrary.utils.RouterManger
import com.ifenghui.commonlibrary.utils.TipsViewManagerInterf
import com.ifenghui.commonlibrary.view.ErrorStatusView
import com.ifenghui.commonlibrary.view.LoadingView
import com.ifenghui.commonlibrary.view.NoDataView
import com.ifenghui.commonlibrary.view.NoNetView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Suppress("DEPRECATION")
abstract class BaseLazyFragment : Fragment(), IBaseView, TipsViewManagerInterf {
    private var mActivity: RxAppCompatActivity? = null
    protected var mMainView: View? = null
    override var mRootView: View? = null//布局容器
    override var mNoNetView: NoNetView? = null//无网络提示
    override var mNoDataView: NoDataView? = null//空数据提示
    override var mErrorStatusView: ErrorStatusView? = null//网络状态异常提示
    private var isViewPrepared: Boolean = false // 标识fragment视图已经初始化完毕
    private var hasFetchData: Boolean = false // 标识已经触发过懒加载数据
    override var mLoadingView: LoadingView? = null //数据加载提示
    private var mFragment: Fragment? = null

    /**
     * 创建fragment时调用
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            mActivity = activity as RxAppCompatActivity?
            mFragment = this
            RouterManger.injectAouter(this)
            EventBus.getDefault().register(this)
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    /**
     * 获取mActivity
     */
    override fun mActivity(): Activity? {
        try {
            if (mActivity == null || mActivity?.isFinishing == true) {
                mActivity = activity as RxAppCompatActivity?
            }
        } catch (e: Exception) {
        }
        return mActivity
    }

    /**
     * 获取 mFragment
     */
    fun mFragment(): Fragment? {
        try {
            if (mFragment == null) {
                mFragment = this
            }
        } catch (e: Exception) {
        }
        return mFragment
    }

    /**
     * 填充页面view时调用
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        try {
            if (mMainView == null) {
                mMainView = inflater.inflate(R.layout.fragment_common_root_layout, container, false)
                //初始化提示信息
                initRootView(mMainView)
                initToolBar()
                initContentView(null)
                //绑定事件
                bindListener()
            } else {
                val viewParent = mMainView?.parent
                if (viewParent != null) {
                    (viewParent as ViewGroup).removeView(mMainView)
                }
                refreshMainData()
            }
        } catch (e: Exception) {
        } catch (e: Error) {
        }
        return mMainView
    }


    /**
     * 页面创建完成时调用
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            isViewPrepared = true
            //如果启用了懒加载就进行懒加载，否则就进行预加载
            if (enableLazyData()) {
                lazyLoad()
            } else {
                loadData()
            }
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    /**
     * fragment可见性发生改变时调用（非本身调用，且嵌套viewpager的时候才能被调用）
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        try {
            //如果启用了懒加载就进行懒加载，
            if (enableLazyData() && isVisibleToUser) {
                lazyLoad()
            }
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    /**
     * 默认不启用懒加载
     */
    open fun enableLazyData(): Boolean {
        return false
    }

    /**
     * 懒加载时调用
     */
    private fun lazyLoad() {
        try {
            if (userVisibleHint && isViewPrepared) {
                loadData()
            }
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    /**
     * 加载数据
     */
    private fun loadData() {
        try {
            if (!hasFetchData && isViewPrepared) {
                hasFetchData = true
                onCreateDelay(null)
            }
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    /**
     * EventBus监听
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun <T> onEvent(event: BaseEvent<T>) = try {
        onEventMain(event)
    } catch (e: Exception) {
    } catch (e: Error) {
    }

    /**
     * 处理 event事件
     */
    open fun <T>onEventMain(event: BaseEvent<T>){

    }

    /**
     * 关闭页面
     */
    override fun finishActivity() {
        try {
            mActivity()?.finish()
        } catch (e: Exception) {

        } catch (e: Error) {

        }
    }

    /**
     * 物理返回键
     */
    override fun onBackPressed() {
        try {
            mActivity()?.onBackPressed()
        } catch (e: Exception) {

        } catch (e: Error) {

        }
    }

    /**
     * fragment中默认不启用顶部导航栏
     */
    override fun enableToolbar(): Boolean {
        return false
    }

    /**
     * 页面注销时调用
     */
    override fun onDestroy() {
        try {
            super.onDestroy()
            EventBus.getDefault().unregister(this)
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    /**
     * 是否是fragment空页面提示样式
     */
    override fun isFragmentLayout(): Boolean {
        return true
    }

    /**
     * mMainView 不为空时用于刷新界面使用
     */
    protected open fun refreshMainData() {

    }

}