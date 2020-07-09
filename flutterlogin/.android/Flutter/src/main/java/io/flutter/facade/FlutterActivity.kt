package io.flutter.facade

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.colin.flutterlogin.R
import com.ifenghui.commonlibrary.application.BaseApplication
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import kotlinx.android.synthetic.main.activity_login.*

class FlutterActivity : BaseLazyActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreateDelay(bundle: Bundle?) {
        //route1是在flutter代码中定义，用来确定显示哪个flutter view
        var flutterView = Flutter.createView(this, lifecycle, "route1")
        flutterView.setBackgroundColor(R.color.white)
        var frameLayout = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addContentView(flutterView, frameLayout)
    }

    override fun onBindLayout(): Int {
        return R.layout.activity_login
    }
}