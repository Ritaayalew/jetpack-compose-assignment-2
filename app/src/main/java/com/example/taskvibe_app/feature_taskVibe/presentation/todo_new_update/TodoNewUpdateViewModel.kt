package com.example.taskvibe_app.feature_taskVibe.presentation.todo_new_update

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskvibe_app.feature_taskVibe.data.di.IoDispatcher
import com.example.taskvibe_app.feature_taskVibe.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoNewUpdateViewModel @Inject constructor(
    private val todoUseCases: TodoUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(TodoNewUpdateState())
    val state: State<TodoNewUpdateState> = _state

    private val errorHandler = CoroutineExceptionHandler {_, e ->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            isLoading = false
        )
    }

    private var currentTodoId: Int? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTodo: UiEvent()
        object Back: UiEvent()
    }

    init {
        savedStateHandle.get<Int>("todoId")?.let { id ->
            if(id != -1){
                viewModelScope.launch(dispatcher + errorHandler){
                    todoUseCases.getTodoItemById(id)?.also { todo ->
                        currentTodoId = id
                        _state.value = _state.value.copy(
                            todo = todo,
                            isLoading = false,
                            isTitleHintVisible = todo.title.isBlank(),
                        )
                    }
                }
            }else{
                _state.value = _state.value.copy(
                    isLoading = false
                )
            }

        }
    }


    fun onEvent(event: TodoNewUpdateEvent){
        when(event){
            TodoNewUpdateEvent.Back -> {
                viewModelScope.launch (dispatcher + errorHandler){
                    _eventFlow.emit(UiEvent.Back)
                }
            }
            is TodoNewUpdateEvent.ChangedTitleFocus -> {
                val shouldTitleHintBeVisible = !event.focusState.isFocused && _state.value.todo.title.isBlank()
                _state.value = _state.value.copy(
                    isTitleHintVisible = shouldTitleHintBeVisible
                )
            }
            is TodoNewUpdateEvent.EnteredTitle -> {
                _state.value = _state.value.copy(
                    todo = _state.value.todo.copy(
                        title = event.value
                    )
                )
            }
        }
    }


}