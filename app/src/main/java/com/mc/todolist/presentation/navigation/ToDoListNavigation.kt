package com.mc.todolist.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mc.todolist.presentation.donelist.DoneList
import com.mc.todolist.presentation.todolist.ToDoList

@Composable
fun ToDoListNavigation(
   paddingValues: PaddingValues,
   navController: NavHostController
) {
   NavHost(
      navController = navController,
      startDestination = ToDoListScreen.ToDoItemsScreen.route
   ) {
      composable(route = ToDoListScreen.ToDoItemsScreen.route) {
         Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            ToDoList()
         }
      }
      composable(route = ToDoListScreen.DoneItemsScreen.route) {
         Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            DoneList()
         }
      }
   }
}
