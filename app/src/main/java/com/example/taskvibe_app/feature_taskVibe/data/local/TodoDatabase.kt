package com.example.taskvibe_app.feature_taskVibe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskvibe_app.feature_taskVibe.data.local.dto.LocalTodoItem

@Database(
    entities = [LocalTodoItem::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDao

    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}