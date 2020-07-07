package com.ifenghui.apilibrary.api.dto

import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
open class RespModel() :Parcelable {
    var status: Status? = null
    var page: Page? = null
    //正常逻辑 返回数据统一放到data里  但是后台搞的太乱 没办法统一
    //    var data: T? = null


    constructor(parcel: Parcel) : this() {
        status = parcel.readParcelable(Status::class.java.classLoader)
        page = parcel.readParcelable(Page::class.java.classLoader)
    }




    override fun toString(): String {
        return "RespModel{" +
                "Status=" + status +
                ", Page='" + page + '\''.toString() +

                '}'.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(status, flags)
        parcel.writeParcelable(page, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RespModel> {
        override fun createFromParcel(parcel: Parcel): RespModel {
            return RespModel(parcel)
        }

        override fun newArray(size: Int): Array<RespModel?> {
            return arrayOfNulls(size)
        }
    }
}
