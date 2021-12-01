package com.softradix.nearbysearch.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchDao {
    @Query("SELECT * FROM search_table")
    fun getAll(): LiveData<List<Response>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(searchList: Response)
}