package com.nandkishor.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandkishor.todolist.db.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import java.time.Instant

// Linking Database to commands or function so we can use it in our file
class TodoViewModel: ViewModel() {

    val todoDao = MainApplication.Companion.todoDatabase.getTodoDao()
    val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()

    fun addTodo(task: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(task = task, time = Date.from(Instant.now())))
        }
    }

    // Function to update a task's completion status
    fun updateTodoStatus(id: Int, newStatus: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateTodoStatus(id, newStatus)
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}