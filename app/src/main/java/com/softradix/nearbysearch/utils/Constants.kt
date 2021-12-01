package com.softradix.nearbysearch.utils


object Constants {

    var BASE_URL:String ="https://api.yelp.com/v3/"
    const val PLACE_API_KEY:String ="AIzaSyC9_aECcOQYBXTqJdeff9N4P87jrkL5tY8"
    const val DELAYED_TIME:Long =2000
    const val DATABASE_NAME = "database.db"
    const val CURRENT_LOCATION:String ="CURRENT_LOCATION"
    const val SOMETHING_WENT_WRONG:String ="Some thing went wrong!"

    internal interface httpcodes {
        companion object {
            const val STATUS_SESSION_EXPIRED = 401
            const val STATUS_API_VALIDATION_ERROR = 422
        }
    }

}