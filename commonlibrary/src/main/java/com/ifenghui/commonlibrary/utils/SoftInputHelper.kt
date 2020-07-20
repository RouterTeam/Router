package com.ifenghui.commonlibrary.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception

class SoftInputHelper {
    companion object {
        /**
         * 显示或隐藏软键盘
         */
        fun hideOrShowKeyboard(view: View?, isShow: Boolean,delayTime:Long) {
            try {
                view?.let { it ->
                    it.postDelayed({
                        val imm =
                            it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        if (isShow) {
                            it.requestFocus()
                            imm.showSoftInput(it, 0)
                        } else {
                            imm.hideSoftInputFromWindow(it.windowToken, 0)
                        }
                    }, delayTime)
                }
            } catch (e: Exception) {
            }


        }
    }
}