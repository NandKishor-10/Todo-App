package com.nandkishor.todolist

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

// Composable for input field, + button and Lazy List
@Composable
fun TodoList(todoViewModel: TodoViewModel) {
    val todoList by todoViewModel.todoList.observeAsState(emptyList())
    var input by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
            .navigationBarsPadding()
    ){
        // Input Field and + Button
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                value = input,
                onValueChange = { input = it },
                shape = RoundedCornerShape(50),
                singleLine = true,
                label = { Text(text = "Enter a Task")},
                modifier = Modifier.weight(1f),

                )

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                if(input.isNotBlank()) {
                    todoViewModel.addTodo(input)
                    input = ""
                    keyboardController?.hide()
                } else {
                    Toast.makeText(context, "No task found", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add task button")
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Task items here in Lazy List
        todoList.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index: Int, item: Todo ->
                        TodoItem(item = item,
                            onDelete = { todoViewModel.deleteTodo(item.id) },
                            todoViewModel = todoViewModel)
                        HorizontalDivider()
                    }
                }
            )
        }
    }
}