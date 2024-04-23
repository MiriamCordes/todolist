package com.mc.todolist.presentation.donelist

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
fun DoneList(
   viewModel: DoneListViewModel = koinViewModel(),
   modifier: Modifier = Modifier
) {
   // TODO move to ViewModel
   // TODO use real data
   val list =
      rememberSaveable {
         mutableListOf("done 1", "done 2", "done 3")
      }
   LazyColumn(modifier = modifier.padding(16.dp)) {
      itemsIndexed(list) { index, label ->
         ToDoListItem(
            ToDoListItemModel(
               id = index,
               label = label,
               checked = true
            )
         )
      }
   }
}
