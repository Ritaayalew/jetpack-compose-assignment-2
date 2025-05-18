package com.example.taskvibe_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskvibe_app.feature_taskVibe.presentation.todo_list.TodoListScreen
import com.example.taskvibe_app.feature_taskVibe.presentation.todo_list.TodoListViewModel
import com.example.taskvibe_app.feature_taskVibe.presentation.todo_detailed.TodoDetailedScreen
import com.example.taskvibe_app.feature_taskVibe.presentation.util.Screen
import com.example.taskvibe_app.ui.theme.TaskVibe_AppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskVibe_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface (
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val listViewModel: TodoListViewModel = hiltViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoItemListScreen.route,
                    ){
                        composable(route = Screen.TodoItemListScreen.route){
                            TodoListScreen(
                                navController = navController,
                                viewModel = listViewModel
                            )
                        }
                        composable(
                            route = Screen.TodoDetailedScreen.route + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId"
                                ){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            TodoDetailedScreen(
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }
}