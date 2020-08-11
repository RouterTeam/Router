package com.ifenghui.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ifenghui.commonlibrary.base.ui.activity.BaseLazyActivity
import com.ifenghui.main.R

class StartTranceActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        startActivity(Intent(this,MainActivity2::class.java))
        finish()
        super.onCreate(savedInstanceState)
    }

    /**
     * 去除页面自带的效果
     */
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_none, R.anim.slide_none)
    }

    /**
     * 禁止点击物理返回键关闭页面
     */
    override fun onBackPressed() {

    }

}