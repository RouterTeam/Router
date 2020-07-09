package io.flutter.provider

import android.content.Context
import android.content.Intent
import com.ifenghui.commonlibrary.provider.IMainProvider
import io.flutter.facade.FlutterActivity

class LoginProvider:IMainProvider {

    /**
     * 分发标志到对应的页面
     */
    override fun distribution(context: Context?, flag: String?, vararg objects: Any?) {
        if ("login"==flag){
            context?.startActivity(Intent(context, FlutterActivity::class.java))
        }
    }
}