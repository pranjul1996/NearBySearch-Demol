package com.softradix.nearbysearch.data


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Region(
    @Embedded
    @SerializedName("center")
    val center: Center
)