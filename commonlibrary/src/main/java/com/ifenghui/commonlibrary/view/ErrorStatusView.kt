package com.ifenghui.commonlibrary.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.ifenghui.commonlibrary.R

class ErrorStatusView : ConstraintLayout {

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
        inflate(context, R.layout.item_erro_status_layout, this)
    }

    fun setErrorStatusBackground(@ColorRes colorResId: Int) {
        this.setBackgroundColor(resources.getColor(colorResId))
    }
}