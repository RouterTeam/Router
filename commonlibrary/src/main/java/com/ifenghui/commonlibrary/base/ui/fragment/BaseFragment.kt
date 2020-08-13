@file:Suppress("DEPRECATION")
package com.ifenghui.commonlibrary.base.ui.fragment
import android.os.Bundle
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
import com.ifenghui.commonlibrary.base.ui.activity.BaseActivity
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
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
            mBinding?.lifecycleOwner=this
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
            Observer { params->
                params?.get(BaseViewModel.ParameterField.CLASS)
                val clz = params?.get(BaseViewModel.ParameterField.CLASS) as Class<*>?
                val bundle = params?.get(BaseViewModel.ParameterField.BUNDLE) as Bundle?
                val isNeedResult=params?.get(BaseViewModel.ParameterField.ISNEED_RESULT) as Boolean
                clz?.let { (mActivity() as BaseLazyActivity)?.startActivity(it, bundle,isNeedResult) }
            })
        mViewModel?.getUC()?.getFinishActivityEvent()?.observe(this, Observer { finishActivity() })
        mViewModel?.getUC()?.getOnBackPressedEvent()?.observe(this, Observer { onBackPressed() })
    }

    /**
     * 创建ViewModel
     */
    override fun createViewModel(): VM {
        return ViewModelProviders.of(this, BaseFactory.getInstance().addCreaterListener(this)).get(onBindViewModel())
    }

    /**
     * 释放资源
     */
    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.let { lifecycle?.removeObserver(it) }
        mBinding?.unbind()
        mViewModel?.onCleared()
    }
}