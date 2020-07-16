package com.colin.login.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.colin.login.R
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import io.flutter.facade.Flutter
import io.flutter.view.FlutterView

class FlutterActivity : BaseLazyActivity() {
    private var flutterView: FlutterView? = null
    override fun onCreateDelay(bundle: Bundle?) {

        val flag = intent.getStringExtra("flag")
        changeTitle(flag)
        addFlutterView(flag)

    }

    override fun onBindLayout(): Int {
        return R.layout.activity_login
    }

    @SuppressLint("ResourceAsColor")
    private fun addFlutterView(flag: String?) {
        //flag是在flutter代码中定义，用来确定显示哪个flutter view
        flutterView = Flutter.createView(this, lifecycle, flag)
        var frameLayout = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        addContentView(flutterView, frameLayout)
    }

    private fun changeTitle(flag: String?) {
        if ("login" == flag)
            resetToolBarTitle("登录")
    }

    override fun isTransStatusBar(): Boolean {
        return true
    }

    override fun enableToolbar(): Boolean {
        return false
    }

    /**
     * 处理物理返回键回调
     */
    override fun onBackPressed() {
        if (flutterView != null)
            flutterView?.popRoute()
        else
            super.onBackPressed()
    }
}