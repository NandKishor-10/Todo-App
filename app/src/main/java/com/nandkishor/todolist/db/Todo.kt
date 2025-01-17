package com.nandkishor.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Entities for Database
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var task: String,
    var time: Date,
    var status: Boolean = false
)
