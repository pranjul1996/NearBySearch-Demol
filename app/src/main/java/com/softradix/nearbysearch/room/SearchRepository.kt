package com.softradix.nearbysearch.room

import androidx.lifecycle.LiveData
import com.softradix.nearbysearch.room.Response
import com.softradix.nearbysearch.room.SearchDao

class SearchRepository(private val searchDao: SearchDao) {

    val allSearchList: LiveData<List<Response>> = searchDao.getAll()

    suspend fun insert(search: Response) {
        searchDao.insertAll(search)
    }
}