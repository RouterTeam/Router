package com.colin.login.provider

import android.content.Context
import android.content.Intent
import com.colin.login.ui.activity.BaseFlutterActivity
import com.colin.login.ui.activity.FlutterActivity
import com.ifenghui.commonlibrary.provider.IMainProvider

class LoginProvider:IMainProvider {

    /**
     * 分发标志到对应的页面
     */
    override fun distribution(context: Context?, flag: String?, vararg objects: Any?) {
//        if ("login"==flag){
//            context?.startActivity(Intent(context,LoginActivity::class.java))
//        }
//        BaseFlutterActivity.toPage(context, TestActvity::class.java)
        val intent = Intent(context, FlutterActivity::class.java)
        intent.putExtra("flag",flag)
        context?.startActivity(intent)
    }
}