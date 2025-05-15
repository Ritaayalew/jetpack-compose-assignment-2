package com.example.taskvibe_app.feature_taskVibe.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskvibe_app.core.util.TodoListStrings
import com.example.taskvibe_app.feature_taskVibe.data.di.IoDispatcher
import com.example.taskvibe_app.feature_taskVibe.domain.use_case.TodoUseCaseResult
import com.example.taskvibe_app.feature_taskVibe.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(TodoListState())
    val state: State<TodoListState> = _state

    private var getTodoItemsJob: Job? = null
    private val errorHandler = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            isLoading = false
        )
    }

    fun getTodoItems(){
        getTodoItemsJob?.cancel()

        getTodoItemsJob = viewModelScope.launch(dispatcher + errorHandler) {
            _state.value = _state.value.copy(isLoading = true)
            val result = todoUseCases.getTodoItems(
                showCompleted = true
            )
            when(result){
                is TodoUseCaseResult.Success -> {
                    _state.value = _state.value.copy(
                        todoItems = result.todoItems,
                        isLoading = false,
                        error = null
                    )
                }
                is TodoUseCaseResult.Error -> {
                    _state.value = _state.value.copy(
                        todoItems = emptyList(),
                        error = TodoListStrings.CANT_GET_TODOS,
                        isLoading = false
                    )
                }
            }
        }
    }
}