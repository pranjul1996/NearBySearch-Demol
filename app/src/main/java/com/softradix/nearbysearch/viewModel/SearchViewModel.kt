package com.softradix.nearbysearch.viewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softradix.nearbysearch.application.NearByApp
import com.softradix.nearbysearch.base.MyViewModel
import com.softradix.nearbysearch.data.Businesse
import com.softradix.nearbysearch.data.SearchDetails
import com.softradix.nearbysearch.repository.SearchRepository
import com.softradix.nearbysearch.utils.Utilities
import kotlinx.coroutines.*

class SearchViewModel : MyViewModel() {

    private val _searchResponseGet = MutableLiveData<List<Businesse>>()
    val searchResponseGet: LiveData<List<Businesse>> = _searchResponseGet
    private val dao = NearByApp.roomDatabase?.searchDao()

    fun getSearchResult(term: String?, location: String?, context: Activity) {
        isLoading.value = true
        if (Utilities.isNetworkAvailable(context)) {
            SearchRepository.search(
                successHandler = {
                    isLoading.value = false
//                searchResponse.value = it
                    viewModelScope.launch(Dispatchers.IO) {
                        it.businesses.forEach { data ->
                            dao?.insertAll(businesse = data)
                        }

                    }
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
        } else {
            isLoading.value = false
        }

//        _searchResponseGet.value = dao?.getAll()

    }

    fun getAll() {
        _searchResponseGet.value = dao?.getAll()
    }

}