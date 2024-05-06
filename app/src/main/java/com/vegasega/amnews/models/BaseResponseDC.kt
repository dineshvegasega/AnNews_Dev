package com.vegasega.amnews.models

import com.google.gson.annotations.SerializedName

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class BaseResponseDC<T>(
    @SerializedName("data")
    val `data`: @RawValue T? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("success")
    val success: Boolean? = false,
    @SerializedName("vendor_id")
    val vendor_id: String? = null,
    @SerializedName("meta")
    val meta: Meta? = null,
) : Parcelable


@Parcelize
data class Meta(
    val first_page: String,
    val last_page: String,
    val per_page: Int,
    val prev_page_url: String,
    val total_items: Int,
    val total_pages: Int
) : Parcelable