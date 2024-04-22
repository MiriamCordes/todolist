package com.mc.todolist.presentation.todolist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mc.todolist.presentation.model.ToDoListItemModel
import com.mc.todolist.presentation.todolistitem.ToDoListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoList(
   viewModel: ToDoListViewModel = koinViewModel(),
   modifier: Modifier = Modifier
) {
   // TODO move to ViewModel
   // TODO use real data
   val list =
      rememberSaveable {
         mutableListOf("todo 1", "todo 2", "todo 3")
      }
   LazyColumn(modifier = modifier.padding(16.dp)) {
      itemsIndexed(list) { index, label ->
         ToDoListItem(
            ToDoListItemModel(
               id = index,
               label = label
            )
         )
      }
   }
}
