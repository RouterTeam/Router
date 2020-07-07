package com.ifenghui.imageloaderlibrary
import android.graphics.drawable.Drawable

interface ImageLoaderListener {
    fun onRequestSuccess(resource: Drawable?): Boolean
    fun onRequestFailed(): Boolean
}