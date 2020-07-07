package com.ifenghui.commonlibrary.utils

import android.annotation.SuppressLint
import android.app.Activity
import com.tbruyelle.rxpermissions2.RxPermissions

object PermissionsManager {

    @SuppressLint("CheckResult")
    fun requestPermission(
        activity:Activity, callback: Callback<Int>?, vararg permissions: String
       ){
        val length = permissions.size
        val grantedNum = intArrayOf(0, 0, 0)
        RxPermissions(activity).requestEach(*permissions)
            .subscribe { permission ->
                when {
                    permission.granted -> {
                        grantedNum[0]++
                        callback?.call(1)
                    }
                    permission.shouldShowRequestPermissionRationale -> {
                        grantedNum[1]++
                        callback?.call(2)

                    }
                    else -> {
                        grantedNum[2]++
                        callback?.call(3)
                    }
                }

                if (grantedNum[0]+grantedNum[1]+grantedNum[2]==length){
                    callback?.call(4)
                }
            }
    }
}