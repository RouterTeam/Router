@file:Suppress("DEPRECATION")

package com.ifenghui.commonlibrary.base.ui.activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ifenghui.commonlibrary.base.factory.BaseFactory
import com.ifenghui.commonlibrary.base.factory.CreateViewModelInter
import com.ifenghui.commonlibrary.base.view.IDataBindlingBaseView
import com.ifenghui.commonlibrary.base.viewmodel.BaseViewModel

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>> : BaseLazyActivity(),
    IDataBindlingBaseView<VM>, CreateViewModelInter {
    protected var mBinding: V? = null
    protected var mViewModel: VM? = null

    /**
     * 初始化数据
     */
    override fun onCreateDelay(bundle: Bundle?) {
        initViewDataBinding()
        initBaseViewObservable()
    }

    /**
     * 初始化DataBinding数据
     */
    private fun initViewDataBinding() {
        try {
            //获得ViewDataBinding
            mBinding = DataBindingUtil.setContentView(this, onBindLayout())
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
        mViewModel?.getUC()?.getFinishActivityEvent()?.observe(this, Observer { finish() })
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
        try {
            mViewModel?.let { lifecycle?.removeObserver(it) }
            mBinding?.unbind()
            mViewModel?.onCleared()
        } catch (e: Exception) {
        }
    }
}