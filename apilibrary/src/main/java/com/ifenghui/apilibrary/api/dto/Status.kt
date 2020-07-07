package com.ifenghui.apilibrary.api.dto

import android.os.Parcel
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Status : Parcelable {
    var code: Int = 0
    var msg: String? = null

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.code)
        dest.writeString(this.msg)
    }


    protected constructor(`in`: Parcel) {
        this.code = `in`.readInt()
        this.msg = `in`.readString()
    }

    companion object CREATOR : Parcelable.Creator<Status> {
        override fun createFromParcel(parcel: Parcel): Status {
            return Status(parcel)
        }

        override fun newArray(size: Int): Array<Status?> {
            return arrayOfNulls(size)
        }
    }
}
