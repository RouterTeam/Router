@file:Suppress("DEPRECATION")
package com.ifenghui.commonlibrary.base.ui.fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ifenghui.commonlibrary.R
import com.ifenghui.commonlibrary.base.factory.BaseFactory
import com.ifenghui.commonlibrary.base.factory.CreateViewModelInter
import com.ifenghui.commonlibrary.base.view.IDataBindlingBaseView
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel<*>> : BaseLazyFragment(),
    IDataBindlingBaseView<VM> , CreateViewModelInter {
    protected var mBinding: V? = null
    protected var mViewModel: VM? = null

    override fun initContentView(mContentView: View?) {
        try {
            mRootView?.findViewById<ViewStub>(R.id.fragment_view_stub_content)?.layoutResource =
                R.layout.fragment_stub_content_layout
            val mContentRootView =
                mRootView?.findViewById<ViewStub>(R.id.fragment_view_stub_content)?.inflate() as ViewGroup?
            mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mActivity()),
                onBindLayout(),
                mContentRootView,
                true
            )
            //创建viewmodel
            mViewModel = createViewModel()
            //绑定variable
            mBinding?.setVariable(onBindVariableId(), mViewModel)
            //声明周期的观察者类
            lifecycle.addObserver(mViewModel ?: createViewModel())
        } catch (e: Exception) {
        } catch (e: Error) {
        }
    }

    /**
     * 绑定事件
     */
    override fun bindListener() {
        super.bindListener()
        initBaseViewObservable()
    }

    /**
     * 初始化
     */
    private fun initBaseViewObservable() {
        mViewModel?.getUC()?.getShowInitLoadViewEvent()
            ?.observe(this, Observer<Boolean?> { })

        mViewModel?.getUC()?.getShowTransLoadingViewEvent()?.observe(this, Observer {
            showLoadingTipsView()
        })
        mViewModel?.getUC()?.getShowNoDataViewEvent()?.observe(this, Observer { showNoDataView() })
        mViewModel?.getUC()?.getCompleteLoadingEvent()?.observe(this, Observer { hideAllTipsView() })

        mViewModel?.getUC()?.getShowNetWorkErrViewEvent()
            ?.observe(this, Observer { showNetWorkErrView() })
        mViewModel?.getUC()?.getShowErrStatusViewEvent()
            ?.observe(this, Observer { showErrStatusView() })
        mViewModel!!.getUC()!!.getStartActivityEvent()!!.observe(
            this,
            Observer<Map<String, Any>?> {
                //                    val clz =
                //                        params[BaseViewModel.ParameterField.CLASS] as Class<*>?
                //                    val bundle = params[BaseViewModel.ParameterField.BUNDLE] as Bundle?
                //                    startActivity(clz, bundle)
            })
        mViewModel?.getUC()?.getFinishActivityEvent()?.observe(this, Observer { finishActivity() })
        mViewModel?.getUC()?.getOnBackPressedEvent()?.observe(this, Observer { onBackPressed() })
    }

    /**
     * 创建ViewModel
     */
    override fun createViewModel(): VM {
        val fractory = mActivity()?.application?.let { BaseFactory.getInstance(it) }
        mActivity()?.application?.let { fractory?.addCreaterListener(it,this) }
        return ViewModelProviders.of(this, fractory).get(onBindViewModel())
    }

    /**
     * 释放资源
     */
    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
        mViewModel?.onCleared()
    }
}