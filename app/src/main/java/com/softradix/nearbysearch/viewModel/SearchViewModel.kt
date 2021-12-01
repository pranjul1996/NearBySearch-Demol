package com.softradix.nearbysearch.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
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
    val searchResponseGet = MutableLiveData<SearchDetails>()
    private val dao = NearByApp.roomDatabase?.searchDao()

    fun getSearchResult(term: String?, location: String?) {
        isLoading.value = true
        SearchRepository.search(
            successHandler = {
                isLoading.value = false
                searchResponse.value = it
                viewModelScope.launch(Dispatchers.IO){
                    dao?.insertAll(searchResponse.value)
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
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO){
            searchResponseGet.value = if(dao?.getAll() == null){
                dao?.getAll()!!
            } else{
                null
            }
        }
    }

}