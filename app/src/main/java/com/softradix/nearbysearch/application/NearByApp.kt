package com.softradix.nearbysearch.application

import android.app.Application
import android.content.Context
import com.softradix.nearbysearch.room.SearchDao
import com.softradix.nearbysearch.room.SearchDataBase
import com.softradix.nearbysearch.utils.Preferences

class NearByApp : Application() {

  companion object{
      var roomDatabase: SearchDataBase? = null


  }

    init {

    }
    override fun onCreate() {
        super.onCreate()
        roomDatabase = SearchDataBase.getSearchDb(this)
        Preferences.initPreferences(this)
    }

}