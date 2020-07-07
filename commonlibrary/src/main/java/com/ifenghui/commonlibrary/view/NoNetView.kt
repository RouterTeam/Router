package com.ifenghui.commonlibrary.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.ifenghui.commonlibrary.R
import com.ifenghui.commonlibrary.utils.Callback
import com.ifenghui.commonlibrary.utils.RxUtils
import kotlinx.android.synthetic.main.item_view_no_net_layout.view.*

class NoNetView : ConstraintLayout {
    private var listener: Callback<Any>? = null

    constructor(context: Context)
            : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet)
            : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        try {
            val inflate: View = inflate(context, R.layout.item_view_no_net_layout, this)
            RxUtils.rxClick(inflate?.btn_net_refresh, object : RxUtils.OnClickInterf {
                override fun onViewClick(view: View, data: Any?) {
                    listener?.call(null)
                }
            }, null)
        } catch (e: Exception) {

        } catch (e: Error) {
        }
    }

    fun setNoNetBackground(@ColorRes colorResId: Int) {
        this.setBackgroundColor(resources.getColor(colorResId))
    }

    fun setRefreshBtnClickListener(listener: Callback<Any>?) {
        this.listener = listener
    }
}