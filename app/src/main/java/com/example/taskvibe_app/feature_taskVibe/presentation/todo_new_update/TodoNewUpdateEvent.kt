package com.example.taskvibe_app.feature_taskVibe.presentation.todo_new_update

import androidx.compose.ui.focus.FocusState

sealed class TodoNewUpdateEvent {
    data class EnteredTitle(val value: String) : TodoNewUpdateEvent()
    data class ChangedTitleFocus(val focusState: FocusState) : TodoNewUpdateEvent()
    object Back: TodoNewUpdateEvent()
}