package com.example.taskvibe_app.feature_taskVibe.data.repo

import android.util.Log
import com.example.taskvibe_app.feature_taskVibe.data.di.IoDispatcher
import com.example.taskvibe_app.feature_taskVibe.data.local.TodoDao
import com.example.taskvibe_app.feature_taskVibe.data.mapper.toLocalTodoItemListFromRemote
import com.example.taskvibe_app.feature_taskVibe.data.mapper.toTodoItem
import com.example.taskvibe_app.feature_taskVibe.data.mapper.toTodoItemListFromLocal
import com.example.taskvibe_app.feature_taskVibe.data.remote.TodoApi
import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem
import com.example.taskvibe_app.feature_taskVibe.domain.repo.TodoListRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

class TodoListRepoImpl(
    private val dao: TodoDao,
    private val api: TodoApi,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): TodoListRepo {
    override suspend fun getAllTodos(): List<TodoItem> {
        getAllTodosFromRemote()
        return dao.getAllTodoItems().toTodoItemListFromLocal()
    }

    override suspend fun getAllTodosFromLocalCache(): List<TodoItem> {
        return dao.getAllTodoItems().toTodoItemListFromLocal()
    }

    override suspend fun getAllTodosFromRemote() {
        return withContext(dispatcher){
            try {
                refreshRoomCache()
            }catch (e: Exception){
                when(e){
                    is UnknownHostException, is ConnectException, is HttpException -> {
                        Log.e("HTTP","Error: No data from Remote")
                        if(isCacheEmpty()){
                            Log.e("Cache","Error: No data from local Room cache")
                            throw Exception("Error: Device offline and\nno data from local Room cache")
                        }
                    }else -> throw e
                }
            }
        }
    }

    private suspend fun refreshRoomCache(){
        val remoteBooks = api.getAllTodos().filterNotNull()
        dao.addAllTodoItems(remoteBooks.toLocalTodoItemListFromRemote())
    }

    private fun isCacheEmpty(): Boolean {
        var empty = true
        if(dao.getAllTodoItems().isNotEmpty()) empty = false
        return empty
    }

    override suspend fun getSingleTodoItemById(id: Int): TodoItem? {
        getAllTodosFromRemote()
        return dao.getSingleTodoItemById(id)?.toTodoItem()
    }
}