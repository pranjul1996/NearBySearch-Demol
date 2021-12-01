package com.softradix.nearbysearch.repository

import com.softradix.nearbysearch.api.ApiHelper
import com.softradix.nearbysearch.data.SearchDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchRepository {
    private val webService = ApiHelper.createService()

    fun search(
        successHandler: (SearchDetails) -> Unit, errorBody: (String) -> Unit,
        onFailure: (Throwable) -> Unit, term: String?, location: String?
    ) {
        webService.search(term?:"pizza", location).enqueue(object : Callback<SearchDetails> {
            override fun onResponse(call: Call<SearchDetails>, response: Response<SearchDetails>) {
                response.body()?.let {
                    successHandler(it)
                }

                response.errorBody()?.let {
                    val error = ApiHelper.handleApiError(it)
                    errorBody(error)
                }            }

            override fun onFailure(call: Call<SearchDetails>, t: Throwable?) {
                t?.let {
                    onFailure(it)
                }
            }

        })
    }

}