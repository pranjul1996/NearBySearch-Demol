package com.softradix.nearbysearch.room

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "search_table")
data class Response(

    @field:SerializedName("total")
    val total: Int? = null,

    @Embedded
    @field:SerializedName("region")
    val region: Region? = null,

    @field:SerializedName("businesses")
    val businesses: List<BusinessesItem?>? = null
)

data class Center(

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
)

data class Coordinates(

    @field:SerializedName("latitude")
    val latitude: Double? = null,

    @field:SerializedName("longitude")
    val longitude: Double? = null
)

data class CategoriesItem(

    @field:SerializedName("alias")
    val alias: String? = null,

    @field:SerializedName("title")
    val title: String? = null
)

data class Region(
    @Embedded
    @field:SerializedName("center")
    val center: Center? = null
)


data class BusinessesItem(
    @field:SerializedName("distance")
    val distance: Double? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    @field:SerializedName("rating")
    val rating: Double? = null,

    @Embedded
    @field:SerializedName("coordinates")
    val coordinates: Coordinates? = null,

    @field:SerializedName("review_count")
    val reviewCount: Int? = null,

    @field:SerializedName("transactions")
    val transactions: List<Any?>? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("display_phone")
    val displayPhone: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("alias")
    val alias: String? = null,

    @Embedded
    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @Embedded
    @field:SerializedName("categories")
    val categories: List<CategoriesItem?>? = null,

    @field:SerializedName("is_closed")
    val isClosed: Boolean? = null
)

data class Location(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("address3")
    val address3: Any? = null,

    @field:SerializedName("address2")
    val address2: Any? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("address1")
    val address1: String? = null,

    @field:SerializedName("display_address")
    val displayAddress: List<String?>? = null,

    @field:SerializedName("state")
    val state: String? = null,

    @field:SerializedName("zip_code")
    val zipCode: String? = null
)
