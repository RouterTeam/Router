package com.ifenghui.apilibrary.api.dto

import android.os.Parcel
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Page : Parcelable {
    var hasNext: Boolean = false
    var pageCount: Int = 0
    var pageNo: Int = 0
    var pageSize: Int = 0
    var rsCount: Int = 0

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeByte(if (this.hasNext) 1.toByte() else 0.toByte())
        dest.writeInt(this.pageCount)
        dest.writeInt(this.pageNo)
        dest.writeInt(this.pageSize)
        dest.writeInt(this.rsCount)
    }


    protected constructor(`in`: Parcel) {
        this.hasNext = `in`.readByte().toInt() != 0
        this.pageCount = `in`.readInt()
        this.pageNo = `in`.readInt()
        this.pageSize = `in`.readInt()
        this.rsCount = `in`.readInt()
    }

    companion object CREATOR : Parcelable.Creator<Page> {
        override fun createFromParcel(parcel: Parcel): Page {
            return Page(parcel)
        }

        override fun newArray(size: Int): Array<Page?> {
            return arrayOfNulls(size)
        }
    }
}
