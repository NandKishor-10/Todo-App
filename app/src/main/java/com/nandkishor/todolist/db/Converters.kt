package com.nandkishor.todolist.db

import androidx.room.TypeConverter
import java.util.Date

// Date converter to store and retrieve from Database
class Converters {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }
}