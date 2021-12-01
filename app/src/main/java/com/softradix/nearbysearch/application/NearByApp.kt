package com.softradix.nearbysearch.application

import android.app.Application
import com.softradix.nearbysearch.utils.Preferences

class NearByApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Preferences.initPreferences(this)
    }

}