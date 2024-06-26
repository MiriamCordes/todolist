package com.mc.todolist.presentation.todolistitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mc.todolist.presentation.model.ToDoListItemModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoListItem(
   itemModel: ToDoListItemModel,
   viewModel: ToDoListItemViewModel = koinViewModel(),
   modifier: Modifier = Modifier
) {
   Row(
      modifier =
         modifier
            .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
   ) {
      Text(
         text = itemModel.label,
         modifier = Modifier.align(Alignment.CenterVertically)
      )
      // TODO handle onCheckedChange
      Checkbox(checked = itemModel.checked, onCheckedChange = {})
   }
}
