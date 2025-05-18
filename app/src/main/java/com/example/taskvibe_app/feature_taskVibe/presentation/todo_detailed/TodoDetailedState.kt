package com.example.taskvibe_app.feature_taskVibe.presentation.todo_detailed

import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem

data class TodoDetailedState(
    val isTitleHintVisible: Boolean = true,
    val isDescriptionHintVisible: Boolean = true,
    val todo: TodoItem = TodoItem(
        title = "",
        completed = false,
        userId = 0,
        id = 0
    ),
    val isLoading: Boolean = true,
    val error: String? = null,
)