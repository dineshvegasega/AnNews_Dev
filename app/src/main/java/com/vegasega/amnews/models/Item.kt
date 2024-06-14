package com.vegasega.amnews.models

import android.os.Parcelable
import com.vegasega.amnews.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class ItemMain(
    var data: @RawValue ArrayList<Item> = ArrayList()
) : Parcelable


@Parcelize
data class ItemMenu(
    var name: String = "",
    var image: Int = 0,
    var isSelected: Boolean = false
) : Parcelable


@Parcelize
data class Item(
    var name: String = "A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any",
    var image: Int = R.drawable.m1,
    var isSelected: Boolean = false,
    var isAdd: Boolean = false,
    var isAddBig: Boolean = false,
    var lang: String = "en",
    var notice_id: String = "",
    var itemList: ArrayList<ItemList> = arrayListOf(
        ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
        ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
        ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
        ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
        ItemList("A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any"),
    )
) : Parcelable


@Parcelize
data class ItemList(
    var name: String = ""
) : Parcelable
