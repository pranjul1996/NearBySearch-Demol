package com.softradix.nearbysearch.room

import android.content.Context
import androidx.room.*
import com.softradix.nearbysearch.data.SearchDetails

@Database(
    entities = [SearchDetails::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class SearchDataBase : RoomDatabase() {
    abstract fun searchDao(): SearchDao


    companion object {
        var roomDb: SearchDataBase? = null

        fun getSearchDb(context: Context?): SearchDataBase? {
            roomDb = context?.let {
                Room.databaseBuilder(
                    it,
                    SearchDataBase::class.java, "search_new_items.db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return roomDb
        }
    }
}