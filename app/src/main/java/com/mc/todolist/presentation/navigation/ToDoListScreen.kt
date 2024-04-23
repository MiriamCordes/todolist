package com.mc.todolist.presentation.navigation

sealed class ToDoListScreen(
   val route: String
) {
   data object ToDoItemsScreen : ToDoListScreen(route = "to_do_items_screen")

   data object DoneItemsScreen : ToDoListScreen(route = "done_items_screen")
}
