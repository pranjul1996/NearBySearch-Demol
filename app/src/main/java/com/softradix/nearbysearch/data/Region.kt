package com.softradix.nearbysearch.data


import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("center")
    val center: Center
)