package com.example.taskvibe_app.feature_taskVibe.presentation.todo_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskvibe_app.core.presentation.components.ArchiveButton
import com.example.taskvibe_app.core.presentation.components.CompleteButton
import com.example.taskvibe_app.core.presentation.components.getTodoColors
import com.example.taskvibe_app.feature_taskVibe.domain.model.TodoItem
import com.example.taskvibe_app.ui.theme.TaskVibe_AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemCard(
    todo: TodoItem,
    modifier: Modifier = Modifier,
    onCompleteClick: () -> Unit,
    onCardClick: () -> Unit,
) {
    val todoColors = getTodoColors(todo = todo)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(24.dp),
        onClick = onCardClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = todo.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = todoColors.textColor,
                fontSize = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(3.dp))
                CompleteButton(onCompleteClick, todoColors.checkColor, todo.completed)
            }
        }
    }
}

@Preview
@Composable
fun TodoItemCardPreview() {
    TaskVibe_AppTheme {
        TodoItemCard(
            TodoItem(
                title = "Cook meal",
                completed = false,
                userId = 2,
                id = 0
            ),
            onCardClick = {},
            onCompleteClick = {}
        )
    }
}

