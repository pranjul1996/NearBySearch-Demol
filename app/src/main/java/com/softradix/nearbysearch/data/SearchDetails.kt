package com.softradix.nearbysearch.data


import com.google.gson.annotations.SerializedName

data class SearchDetails(
    @SerializedName("businesses")
    val businesses: ArrayList<Businesse>,
    @SerializedName("region")
    val region: Region,
    @SerializedName("total")
    val total: Int
)