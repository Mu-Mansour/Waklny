package com.example.waklny.Pojo

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConvertor {

    @TypeConverter
    fun fromAnyToString(any: Any?):String{
        any?.let {
            return it as  String
        } ?: return ""

    }
    @TypeConverter
    fun fromStringToAny(string: String?):Any{
        string?.let {
            return it as  Any
        } ?: return ""

    }
}