package com.example.taskvibe_app.feature_taskVibe.domain.use_case

import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem
import com.example.taskvibe_app.feature_taskVibe.domain.repo.TodoListRepo
import javax.inject.Inject

class TodoUseCases @Inject constructor(
    private val repo: TodoListRepo
) {

    suspend fun getTodoItemById(id: Int): TodoItem? {
        return repo.getSingleTodoItemById(id)
    }

    suspend fun getTodoItems(
        showCompleted: Boolean = true
    ): TodoUseCaseResult {
        var todos = repo.getAllTodosFromLocalCache()

        if(todos.isEmpty()){
            todos = repo.getAllTodos()
        }

        val filteredTodos = if (showCompleted){
            todos
        } else {
            todos.filter{ !it.completed }
        }

        return TodoUseCaseResult.Success(filteredTodos)
    }

}




sealed class TodoUseCaseResult {
    data class Success(val todoItems: List<TodoItem>) : TodoUseCaseResult()
    data class Error(val message: String) : TodoUseCaseResult()
}