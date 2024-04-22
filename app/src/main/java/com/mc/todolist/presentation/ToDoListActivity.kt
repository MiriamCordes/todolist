package com.mc.todolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mc.todolist.presentation.navigation.ToDoListNavigation
import com.mc.todolist.presentation.navigation.getBottomNavigationItems

class ToDoListActivity : ComponentActivity() {
   // TODO move texts to file
   // TODO move colors to file

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge(
         statusBarStyle = SystemBarStyle.dark(scrim = android.graphics.Color.GRAY),
         navigationBarStyle = SystemBarStyle.dark(scrim = android.graphics.Color.GRAY)
      )
      setContent {
         MainScreen(modifier = Modifier.safeDrawingPadding())
      }
   }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
   val navController = rememberNavController()
   val onAddTodoListItemClick = {
      // TODO
   }
   Scaffold(
      topBar = {
         TopAppBar(
            title = {
               Text(text = "To Do List", fontWeight = FontWeight.SemiBold)
            },
            colors =
               TopAppBarDefaults.topAppBarColors(
                  containerColor = Color.LightGray,
                  titleContentColor = Color.DarkGray
               )
         )
      },
      floatingActionButton = {
         FloatingActionButton(
            modifier = modifier,
            contentColor = Color.DarkGray,
            containerColor = Color.LightGray,
            onClick = onAddTodoListItemClick
         ) {
            Text(text = "+", fontSize = 20.sp)
         }
      },
      bottomBar = { ToDoListBottomBar(navController = navController) }
   ) { paddingValues ->
      ToDoListNavigation(
         navController = navController,
         paddingValues = paddingValues
      )
   }
}

@Composable
fun ToDoListBottomBar(navController: NavController) {
   val currentBottomBarItem = rememberSaveable { mutableIntStateOf(0) }
   NavigationBar(
      containerColor = Color.LightGray,
      contentColor = Color.DarkGray
   ) {
      getBottomNavigationItems().forEachIndexed { index, item ->
         NavigationBarItem(
            selected = index == currentBottomBarItem.intValue,
            label = {
               Text(
                  text = item.label,
                  color = Color.DarkGray
               )
            },
            onClick = {
               currentBottomBarItem.intValue = index
               navController.navigate(item.route)
            },
            icon = {
               Icon(
                  imageVector = item.icon,
                  contentDescription = item.label,
                  tint = Color.DarkGray
               )
            }
         )
      }
   }
}
