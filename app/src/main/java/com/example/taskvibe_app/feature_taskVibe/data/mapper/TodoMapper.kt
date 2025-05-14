package com.example.taskvibe_app.feature_taskVibe.data.mapper

import com.example.taskvibe_app.feature_taskVibe.data.local.dto.LocalTodoItem
import com.example.taskvibe_app.feature_taskVibe.data.remote.dto.RemoteTodoItem
import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem

fun TodoItem.toLocalTodoItem(): LocalTodoItem{
    return LocalTodoItem(
        title = title,
        userId = userId,
        completed = completed,
        id = id
    )
}

fun TodoItem.toRemoteTodoItem(): RemoteTodoItem {
    return RemoteTodoItem(
        title = title,
        userId = userId,
        completed = completed,
        id = id
    )
}

fun LocalTodoItem.toTodoItem(): TodoItem{
    return TodoItem(
        title = title,
        userId = userId,
        completed = completed,
        id = id
    )
}

fun LocalTodoItem.toRemoteTodoItem(): RemoteTodoItem{
    return RemoteTodoItem(
        title = title,
        userId = userId,
        completed = completed,
        id = id
    )
}

fun RemoteTodoItem.toTodoItem(): TodoItem{
    return TodoItem(
        title = title,
        userId = userId,
        completed = completed,
        id = id
    )
}

fun RemoteTodoItem.toLocalTodoItem(): LocalTodoItem{
    return LocalTodoItem(
        title = title,
        userId = userId,
        completed = completed,
        id = id
    )
}

fun List<TodoItem>.toLocalTodoItemList(): List<LocalTodoItem>{
    return this.map {todo ->
        LocalTodoItem(
            title = todo.title,
            userId = todo.userId,
            completed = todo.completed,
            id = todo.id
        )
    }
}

fun List<TodoItem>.toRemoteTodoItemList(): List<RemoteTodoItem>{
    return this.map {todo ->
        RemoteTodoItem(
            title = todo.title,
            userId = todo.userId,
            completed = todo.completed,
            id = todo.id
        )
    }
}

fun List<LocalTodoItem>.toTodoItemListFromLocal(): List<TodoItem>{
    return this.map {todo ->
        TodoItem(
            title = todo.title,
            userId = todo.userId,
            completed = todo.completed,
            id = todo.id
        )
    }
}

fun List<LocalTodoItem>.toRemoteTodoItemListFromLocal(): List<RemoteTodoItem>{
    return this.map {todo ->
        RemoteTodoItem(
            title = todo.title,
            userId = todo.userId,
            completed = todo.completed,
            id = todo.id
        )
    }
}

fun List<RemoteTodoItem>.toTodoItemListFromRemote(): List<TodoItem>{
    return this.map {todo ->
        TodoItem(
            title = todo.title,
            userId = todo.userId,
            completed = todo.completed,
            id = todo.id
        )
    }
}

fun List<RemoteTodoItem>.toLocalTodoItemListFromRemote(): List<LocalTodoItem>{
    return this.map {todo ->
        LocalTodoItem(
            title = todo.title,
            userId = todo.userId,
            completed = todo.completed,
            id = todo.id
        )
    }
}