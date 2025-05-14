package com.example.taskvibe_app.feature_taskVibe.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class LocalTodoItem(
    val title: String,
    val completed: Boolean,
    val userId: Int,
    @PrimaryKey
    val id: Int
)
