package com.ifenghui.commonlibrary.base.event

open class BaseEvent<T> {
    var tag: Int = 0
    var data: T? = null
    var datas: Array<T>? = null

    fun RefreshEvent(tag: Int) {
        this.tag = tag
    }

    fun RefreshEvent(tag: Int, data: T) {
        this.tag = tag
        this.data = data
    }

    fun RefreshEvent(tag: Int, datas: Array<T>?) {
        this.tag = tag
        this.datas = datas
    }
}