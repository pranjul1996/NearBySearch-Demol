package com.softradix.nearbysearch.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softradix.nearbysearch.application.NearByApp
import com.softradix.nearbysearch.base.MyViewModel
import com.softradix.nearbysearch.data.SearchDetails
import com.softradix.nearbysearch.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : MyViewModel() {

    val searchResponse = MutableLiveData<SearchDetails>()
    private val dao = NearByApp.roomDatabase?.searchDao()

    fun getSearchResult(term: String?, location: String?) {
        isLoading.value = true
        SearchRepository.search(
            successHandler = {
                isLoading.value = false
                searchResponse.value = it
                insert()

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

    fun insert() = viewModelScope.launch(Dispatchers.IO){
        dao?.insertAll(searchResponse.value)

    }

}