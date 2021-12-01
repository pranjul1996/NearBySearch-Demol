package com.softradix.nearbysearch.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softradix.nearbysearch.application.NearByApp
import com.softradix.nearbysearch.base.MyViewModel
import com.softradix.nearbysearch.data.Businesse
import com.softradix.nearbysearch.data.SearchDetails
import com.softradix.nearbysearch.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : MyViewModel() {

    val searchResponse = MutableLiveData<SearchDetails>()
    val searchResponseGet = MutableLiveData<List<Businesse>>()
    private val dao = NearByApp.roomDatabase?.searchDao()

    fun getSearchResult(term: String?, location: String?) {
        isLoading.value = true
        SearchRepository.search(
            successHandler = {
                isLoading.value = false
                searchResponse.value = it
                viewModelScope.launch(Dispatchers.IO){
                   it.businesses.forEach{ data ->
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
    }

//    fun getAll(){
//        var data: SearchDetails? = null
//        viewModelScope.launch(Dispatchers.IO){
//            data = if(dao?.getAll() == null){
//                dao?.getAll()
//            }else{
//                null
//            }
//        }
//        searchResponseGet.value = data
//    }

    fun getData(): LiveData<List<Businesse>>{

        var data: List<Businesse>? = null

        viewModelScope.launch(Dispatchers.IO){
            data = dao?.getAll()
        }
        searchResponseGet.value = data
        return searchResponseGet
    }

}