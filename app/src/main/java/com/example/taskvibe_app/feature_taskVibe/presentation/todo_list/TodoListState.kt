package com.example.taskvibe_app.feature_taskVibe.presentation.todo_list

import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem

data class TodoListState(
    val todoItems: List<TodoItem> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val showCompleted: Boolean = true,
)