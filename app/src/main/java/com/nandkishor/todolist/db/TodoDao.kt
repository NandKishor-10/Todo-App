package com.nandkishor.todolist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nandkishor.todolist.Todo


// Data Access Object for Database
@Dao
interface TodoDao{

    @Query("SELECT * FROM Todo ORDER BY time DESC")
    fun getAllTodo(): LiveData<List<Todo>>

    @Insert
    fun addTodo(todo: Todo)

    @Query("UPDATE Todo SET status = :newStatus WHERE id = :id")
    fun updateTodoStatus(id: Int, newStatus: Boolean)

    @Query("DELETE FROM Todo WHERE id = :id")
    fun deleteTodo(id: Int)

}