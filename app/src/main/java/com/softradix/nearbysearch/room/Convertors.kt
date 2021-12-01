package com.softradix.nearbysearch.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.softradix.nearbysearch.data.Businesse
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Convertors {

    val jsonAdapter: Gson = Gson()

    @TypeConverter
    fun objectToStr(listMyModel: List<Businesse>?): String? {
        return jsonAdapter.toJson(listMyModel)
    }

    @TypeConverter
    fun strToObject(jsonStr: String?): ArrayList<Businesse>? {
        val listType: Type = object : TypeToken<ArrayList<Businesse?>?>() {}.type
        return jsonAdapter.fromJson(jsonStr, listType)
    }

}