package com.nandkishor.todolist

import android.app.Application
import androidx.room.Room
import com.nandkishor.todolist.db.TodoDatabase

// Loading Database when application is opened
class MainApplication: Application() {
    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        todoDatabase = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.NAME
        ).build()
    }
}