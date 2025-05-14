package com.example.taskvibe_app.feature_taskVibe.domain.repo

import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem

interface TodoListRepo {
    suspend fun getAllTodos(): List<TodoItem>
    suspend fun getAllTodosFromLocalCache(): List<TodoItem>
    suspend fun getAllTodosFromRemote()
    suspend fun getSingleTodoItemById(id: Int): TodoItem?
}