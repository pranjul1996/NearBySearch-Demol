package com.softradix.nearbysearch.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.softradix.nearbysearch.data.Businesse
import com.google.gson.reflect.TypeToken




class Convertors {

    val jsonAdapter: Gson = Gson()

    @TypeConverter
    fun listBusinesseToJsonStr(listMyModel: List<Businesse>?): String? {
        return jsonAdapter.toJson(listMyModel)
    }

    @TypeConverter
    fun jsonStrToListBusinesse(jsonStr: String?): ArrayList<Businesse>? {
        val listType =
            object : TypeToken<ArrayList<Businesse>?>() {}.type
        return jsonAdapter.fromJson(jsonStr, listType)
    }


}