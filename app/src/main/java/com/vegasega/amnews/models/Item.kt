package com.vegasega.amnews.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ItemMain (
    var data: @RawValue ArrayList<Item> = ArrayList()
) : Parcelable



@Parcelize
data class ItemMenu (
    var name: String = "",
    var image: Int = 0,
    var isSelected: Boolean = false
) : Parcelable


@Parcelize
    data class Item (
        var name: String = "",
        var image: Int = 0,
        var isSelected: Boolean = false,
        var isAd: Boolean = false,
        var itemList: ArrayList<ItemList> = ArrayList()
    ) : Parcelable


@Parcelize
    data class ItemList (
        var name: String = ""
    ) : Parcelable
