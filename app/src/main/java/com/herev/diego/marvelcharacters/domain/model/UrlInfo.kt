package com.herev.diego.marvelcharacters.domain.model

import android.os.Parcel
import android.os.Parcelable

data class UrlInfo(val type : String?, val url : String?) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UrlInfo> {
        override fun createFromParcel(parcel: Parcel): UrlInfo {
            return UrlInfo(parcel)
        }

        override fun newArray(size: Int): Array<UrlInfo?> {
            return arrayOfNulls(size)
        }
    }

}
