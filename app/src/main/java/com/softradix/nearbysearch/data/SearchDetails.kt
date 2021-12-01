package com.softradix.nearbysearch.data


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "table_data")
data class SearchDetails(

    @SerializedName("businesses")
    val businesses: ArrayList<Businesse>,
    @Embedded
    @SerializedName("region")
    val region: Region,

    @PrimaryKey(autoGenerate = true)
    @SerializedName("total")
    val total: Int
)