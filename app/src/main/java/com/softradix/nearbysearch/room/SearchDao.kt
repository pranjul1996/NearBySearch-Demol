package com.softradix.nearbysearch.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softradix.nearbysearch.data.Businesse
import com.softradix.nearbysearch.data.SearchDetails

@Dao
interface SearchDao {
    @Query("SELECT * FROM table_data")
    fun getAll(): List<Businesse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(businesse: Businesse?)
}