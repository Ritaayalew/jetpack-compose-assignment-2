package com.example.taskvibe_app.feature_taskVibe.data.remote

import com.example.taskvibe_app.feature_taskVibe.data.remote.dto.RemoteTodoItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TodoApi {
    @GET("todos")
    suspend fun getAllTodos(): List<RemoteTodoItem>

    @GET("todos/{id}")
    suspend fun getSingleTodoById(@Path("id") id: Int): Map<String, RemoteTodoItem>

}