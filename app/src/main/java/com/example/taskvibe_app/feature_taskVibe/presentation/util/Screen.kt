package com.example.taskvibe_app.feature_taskVibe.presentation.util

sealed class Screen(val route: String){
    object TodoItemListScreen: Screen("todoItemList_screen")
    object TodoDetailedScreen: Screen("todoDetailed_screen")
}