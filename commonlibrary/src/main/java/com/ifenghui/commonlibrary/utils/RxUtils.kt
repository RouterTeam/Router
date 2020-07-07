package com.ifenghui.commonlibrary.utils

import android.annotation.SuppressLint
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit

class RxUtils {
    companion object {
        @SuppressLint("CheckResult")
        fun rxClick(clickView: View, onClickInterf: OnClickInterf?, data: Any?) {
            RxView.clicks(clickView) // 以 Observable 形式来反馈点击事件
                .throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe {
                    onClickInterf?.onViewClick(clickView, data)
                }
        }
    }

    open interface OnClickInterf {
        fun onViewClick(view: View, data: Any?)
    }
}