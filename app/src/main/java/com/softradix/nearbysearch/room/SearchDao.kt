package com.softradix.nearbysearch.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softradix.nearbysearch.data.SearchDetails

@Dao
interface SearchDao {
    @Query("SELECT * FROM new_search_table")
    suspend fun getAll(): SearchDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(searchList: SearchDetails?)
}