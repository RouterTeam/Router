package io.flutter.facade

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.colin.flutterlogin.R
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import io.flutter.embedding.android.FlutterView

class FlutterActivity : BaseLazyActivity() {
    override fun onCreateDelay(bundle: Bundle?) {
        val flag = intent.getStringExtra("flag")
        changeTitle(flag)
        addFlutterView(flag)

    }

    override fun onBindLayout(): Int {
        return R.layout.activity_login
    }

    @SuppressLint("ResourceAsColor")
    private fun addFlutterView(flag:String?){
        //flag是在flutter代码中定义，用来确定显示哪个flutter view
        var flutterView = Flutter.createView(this, lifecycle, flag)
//        var flutterView = FlutterView(this, FlutterView.RenderMode.surface, FlutterView.TransparencyMode.transparent)
        var frameLayout = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addContentView(flutterView, frameLayout)

//        新建FlutterView
//        var flutterView = FlutterView(this, FlutterView.RenderMode.surface, FlutterView.TransparencyMode.transparent)
//        若使用缓存Engine同理需要设置
//        FlutterFragment.withCachedEngine(CACHE_ENGINE_ID).renderMode(FlutterView.RenderMode.surface).transparencyMode(FlutterView.TransparencyMode.transparent)
    }

    private fun changeTitle(flag:String?){
        if ("login"==flag)
            resetToolBarTitle("登录")
    }

    override fun isTransStatusBar(): Boolean {
        return true
    }

    override fun enableToolbar(): Boolean {
        return false
    }
}