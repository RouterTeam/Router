package com.colin.login.ui.activity

import android.os.Bundle
import com.colin.login.R
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity

class LoginActivity :BaseLazyActivity() {
    override fun onCreateDelay(bundle: Bundle?) {

    }

    override fun onBindLayout(): Int {
        return R.layout.activity_login
    }
}