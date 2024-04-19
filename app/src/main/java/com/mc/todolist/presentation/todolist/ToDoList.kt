package com.mc.todolist.presentation.todolist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoList(
   viewModel: ToDoListViewModel = koinViewModel(),
   modififer: Modifier = Modifier
) {
}
