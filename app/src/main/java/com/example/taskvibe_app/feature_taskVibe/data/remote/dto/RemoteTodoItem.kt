package com.example.taskvibe_app.feature_taskVibe.data.remote.dto

data class RemoteTodoItem(
    val title: String,
    val completed: Boolean,
    val userId: Int,
    val id: Int
)