package com.ifenghui.imageloaderlibrary

interface ImageLoaderListener  {
    fun <T>onRequestSuccess(resource: T?): Boolean
    fun onRequestFailed(): Boolean
}