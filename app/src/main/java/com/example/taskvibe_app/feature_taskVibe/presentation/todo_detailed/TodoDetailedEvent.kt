package com.example.taskvibe_app.feature_taskVibe.presentation.todo_detailed

import androidx.compose.ui.focus.FocusState

sealed class TodoDetailedEvent {
    data class EnteredTitle(val value: String) : TodoDetailedEvent()
    data class ChangedTitleFocus(val focusState: FocusState) : TodoDetailedEvent()
    object Back: TodoDetailedEvent()
}