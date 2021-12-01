package com.softradix.roomdbmvvm.sirDemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.softradix.nearbysearch.room.Response
import com.softradix.nearbysearch.room.SearchDataBase
import com.softradix.nearbysearch.room.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val allSearchList: LiveData<List<Response>>
    private val repository: SearchRepository?
    private val dao = SearchDataBase.getSearchDb(application)?.searchDao()

    init {
//        val dao = SearchDataBase.getSearchDb(application)?.searchDao()
        repository = dao?.let { SearchRepository(it) }
        allSearchList = repository?.allSearchList!!
    }

    fun addSearchData(searchData: Response) = viewModelScope.launch(Dispatchers.IO) {
        repository?.insert(searchData)
    }

    fun getUsersDataBase(): LiveData<List<Response>>? {
        return dao?.getAll()
    }
}