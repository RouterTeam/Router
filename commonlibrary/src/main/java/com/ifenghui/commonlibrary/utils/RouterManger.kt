package com.ifenghui.commonlibrary.utils

import com.alibaba.android.arouter.launcher.ARouter
import com.ifenghui.commonlibrary.application.BaseApplication

class RouterManger {
    companion object {
        @JvmStatic
        fun initRouter(context: BaseApplication) {
            ARouter.openLog()
            ARouter.openDebug()
            ARouter.init(context)
        }

        fun injectAouter(any: Any) {
            ARouter.getInstance().inject(any)
        }

        fun startAct(path: String) {
            ARouter.getInstance().build(path).navigation()
        }
        @JvmStatic
        fun destoryRouter(){
            ARouter.getInstance().destroy()
        }
    }
}