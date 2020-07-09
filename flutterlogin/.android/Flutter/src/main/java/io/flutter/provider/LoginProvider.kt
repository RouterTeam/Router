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
        val intent = Intent(context, FlutterActivity::class.java)
        intent.putExtra("flag",flag)
        context?.startActivity(intent)
    }
}