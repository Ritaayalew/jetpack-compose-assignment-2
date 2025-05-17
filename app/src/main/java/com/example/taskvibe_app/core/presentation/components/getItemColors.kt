package com.example.taskvibe_app.core.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem

data class TodoItemColors(
    val backgroundColor: Color,
    val textColor: Color,
    val archiveIconColor: Color,
    val checkColor: Color
)

@Composable
fun getTodoColors(todo: TodoItem): TodoItemColors {

    return TodoItemColors(
        backgroundColor = MaterialTheme.colorScheme.surfaceDim.copy(alpha = 0.6f),
        textColor = Color.Black,
        archiveIconColor = MaterialTheme.colorScheme.onSecondary,
        checkColor = if (todo.completed) Color(0xFF006400) else Color.Black
    )

}