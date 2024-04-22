package com.mc.todolist.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
   val label: String,
   val icon: ImageVector,
   val route: String
)

fun getBottomNavigationItems() =
   listOf(
      BottomNavigationItem(
         label = "To Do",
         icon = Icons.Filled.Home,
         route = ToDoListScreen.ToDoItemsScreen.route
      ),
      BottomNavigationItem(
         label = "Done",
         icon = Icons.Filled.Check,
         route = ToDoListScreen.DoneItemsScreen.route
      )
   )
