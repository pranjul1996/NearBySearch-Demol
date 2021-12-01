package com.softradix.nearbysearch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Response::class],
    version = 1,
    exportSchema = false
)

abstract class SearchDataBase : RoomDatabase() {
    abstract fun searchDao(): SearchDao


    companion object {
        var roomDb: SearchDataBase? = null

        fun getSearchDb(context: Context): SearchDataBase? {
            roomDb = Room.databaseBuilder(
                context,
                SearchDataBase::class.java, "search_items.db"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return roomDb
        }
    }
}