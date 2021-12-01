package com.softradix.nearbysearch.room

import android.content.Context
import androidx.room.*
import com.softradix.nearbysearch.data.SearchDetails
import com.softradix.nearbysearch.utils.Constants

@Database(
    entities = [SearchDetails::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class SearchDataBase : RoomDatabase() {
    abstract fun searchDao(): SearchDao


    companion object {
        var roomDb: SearchDataBase? = null

        fun getSearchDb(context: Context): SearchDataBase? {
            roomDb = Room.databaseBuilder(
                context,
                SearchDataBase::class.java, Constants.DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return roomDb
        }
    }
}