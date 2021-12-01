package com.softradix.nearbysearch.viewModel

import androidx.lifecycle.MutableLiveData
import com.softradix.nearbysearch.base.MyViewModel
import com.softradix.nearbysearch.data.SearchDetails
import com.softradix.nearbysearch.repository.SearchRepository

class SearchViewModel : MyViewModel() {
    val apiError = MutableLiveData<String>()
    val onFailure = MutableLiveData<Throwable>()
    val searchResponse = MutableLiveData<SearchDetails>()


    fun getSearchResult(term: String?, location: String?) {
        isLoading.value = true
        SearchRepository.search(
            successHandler = {
                isLoading.value = false
                searchResponse.value = it
            },
            errorBody = {
                isLoading.value = false
                apiError.value = it
            },
            onFailure = {
                isLoading.value = false
                onFailure.value = it
            },
            term = term, location = location
        )
    }
}