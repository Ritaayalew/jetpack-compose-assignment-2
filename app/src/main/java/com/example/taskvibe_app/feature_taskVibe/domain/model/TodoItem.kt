package com.example.taskvibe_app.feature_taskVibe.domain.model

data class TodoItem(
    val title: String,
    val completed: Boolean,
    val userId: Int,
    val id: Int
)