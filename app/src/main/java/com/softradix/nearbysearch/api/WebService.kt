package com.softradix.nearbysearch.api

import com.softradix.nearbysearch.data.SearchDetails
import retrofit2.Call
import retrofit2.http.*

interface WebService {

    @Headers("Accept: " + "application/json")
    @GET("businesses/search")
    fun search(@Query("term") term: String?, @Query("location") location: String?): Call<SearchDetails>
}