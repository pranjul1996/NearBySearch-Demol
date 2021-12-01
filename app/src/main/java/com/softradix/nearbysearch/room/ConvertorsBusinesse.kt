package com.softradix.nearbysearch.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softradix.nearbysearch.data.Businesse
import com.softradix.nearbysearch.data.Category
import java.lang.reflect.Type


class ConvertorsBusinesse {

    @TypeConverter
    fun fromString(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromCategoryString(value: String?): List<Category?>? {
        val listType: Type = object : TypeToken<List<Category?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCategoryList(list: List<Category?>?): String? {
        return Gson().toJson(list)
    }
/*    @TypeConverter
    fun fromBusinesseString(value: String?): List<Businesse?>? {
        val listType: Type = object : TypeToken<ArrayList<Businesse?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromBusinesseList(list: List<Businesse?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun objectToBusinessJson(value: Businesse?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToBusinessObject(string: String?): Businesse? {
        return Gson().fromJson(string, Businesse::class.java)
    }*/
/*
    @TypeConverter
    fun objectToCategoryJson(value: Category?): String? = Gson().toJson(value)

    @TypeConverter
    fun stringToCategoryObject(string: String?): Category? {
        return Gson().fromJson(string, Category::class.java)
    }
*/


}