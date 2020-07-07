package com.ifenghui.commonlibrary.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.ifenghui.commonlibrary.R
import kotlinx.android.synthetic.main.item_view_no_data_layout.view.*

class NoDataView : ConstraintLayout {
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
     inflate(context, R.layout.item_view_no_data_layout, this)
    }

    fun setNoDataBackground(@ColorRes colorResId: Int) {
        this.setBackgroundColor(resources.getColor(colorResId))
    }

    fun setNoDataView(@DrawableRes imgResId: Int) {
        this.img_no_data.setImageResource(imgResId)
    }
}
