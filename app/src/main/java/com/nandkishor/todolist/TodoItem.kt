package com.nandkishor.todolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import java.text.SimpleDateFormat
import java.util.Locale

// Composable for each item in task list
@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit, todoViewModel: TodoViewModel) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.height(60.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.CenterStart)
        ) {
            RadioButton(selected = item.status, onClick = {
                todoViewModel.updateTodoStatus(item.id, !item.status)
            })

            Text(
                text = buildAnnotatedString {
                    if (item.status) {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                            append(item.task)
                        }
                    } else {
                        append(item.task)
                    }
                },
                fontSize = 16.sp
            )
        }

        val constraints = ConstraintSet {
            val trashCan = createRefFor("trashCan")
            val timeStamp = createRefFor("timeStamp")

            constrain(trashCan) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }

            constrain(timeStamp) {
                top.linkTo(trashCan.bottom)
                bottom.linkTo(parent.bottom,
                    margin = 10.dp)
                end.linkTo(parent.end)
            }
        }

        ConstraintLayout(constraints, modifier = Modifier.wrapContentSize()) {
            IconButton(
                onClick = onDelete,
                modifier = Modifier.layoutId("trashCan")
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.DarkGray
                )
            }

            Text(
                text = SimpleDateFormat("hh:mm a, dd-MM-yyyy", Locale.ENGLISH).format(item.time),
                modifier = Modifier.layoutId("timeStamp"),
                Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}