package com.ifenghui.commonlibrary.base.event

open class BaseEvent<T> {
    var tag: Int = 0
    var data: T? = null
    var datas: Array<T>? = null

    constructor()
    constructor(tag: Int) {
        this.tag = tag
    }

    constructor(tag: Int, data: T) {
        this.tag = tag
        this.data = data
    }

    constructor(tag: Int, datas: Array<T>?) {
        this.tag = tag
        this.datas = datas
    }
}