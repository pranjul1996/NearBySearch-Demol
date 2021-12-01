package com.softradix.nearbysearch.data

import com.google.gson.annotations.SerializedName

data class Status(

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("message")
    val message: String? = null
)
