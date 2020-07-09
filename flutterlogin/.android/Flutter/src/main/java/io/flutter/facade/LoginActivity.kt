package io.flutter.facade

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import com.colin.flutterlogin.R
import com.ifenghui.commonlibrary.application.BaseApplication
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity

class LoginActivity : BaseLazyActivity() {
    override fun onCreateDelay(bundle: Bundle?) {
        Log.e("--------",BaseApplication.mCurrentUser.nick)

        //route1是在flutter代码中定义，用来确定显示哪个flutter view
        var flutterView = Flutter.createView(this, lifecycle, "route2")
        var frameLayout = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addContentView(flutterView, frameLayout)
    }

    override fun onBindLayout(): Int {
        return R.layout.activity_login
    }
}