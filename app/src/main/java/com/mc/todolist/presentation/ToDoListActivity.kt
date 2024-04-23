package com.mc.todolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mc.todolist.presentation.navigation.ToDoListNavigation
import com.mc.todolist.presentation.navigation.getBottomNavigationItems
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

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
   val showBottomSheet = remember { mutableStateOf(false) }
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
            onClick = {
               showBottomSheet.value = true
            }
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
      if (showBottomSheet.value) {
         AddToDoBottomSheet {
            showBottomSheet.value = false
         }
      }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoBottomSheet(hideBottomSheet: () -> Unit) {
   val sheetState = rememberModalBottomSheetState()
   val coroutineScope = rememberCoroutineScope()
   val onAddToDoItemDone = {
      coroutineScope
         .launch {
            sheetState.hide()
         }
         .invokeOnCompletion {
            if (!sheetState.isVisible) {
               hideBottomSheet()
            }
         }
   }
   ModalBottomSheet(
      onDismissRequest = {
         hideBottomSheet()
      },
      sheetState = sheetState
   ) {
      ShowKeyBoard(onAddToDoItemDone)
   }
}

@Composable
fun ShowKeyBoard(
   onAddItemDone: () -> DisposableHandle,
   modifier: Modifier = Modifier
) {
   val keyboardController = LocalSoftwareKeyboardController.current
   val focusRequester = remember { FocusRequester() }
   val text = remember { mutableStateOf("Add to do") }
   val onValueChange: (String) -> Unit = {
      text.value = it
   }
   LaunchedEffect(Unit) {
      focusRequester.requestFocus()
   }
   Column(modifier = modifier.padding(16.dp)) {
      Text(
         text = "Add To Do Item",
         fontWeight = FontWeight.SemiBold,
         fontSize = 18.sp,
         color = Color.DarkGray,
         textAlign = TextAlign.Center,
         modifier = modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.padding(vertical = 16.dp))
      TextField(
         value = text.value,
         onValueChange = onValueChange,
         keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
         keyboardActions =
            KeyboardActions(
               onDone = {
                  keyboardController?.hide()
                  onAddItemDone()
               }
            ),
         trailingIcon = {
            Icon(
               imageVector = Icons.Filled.Check,
               contentDescription = "Add",
               tint = Color.DarkGray,
               modifier =
                  Modifier.clickable {
                     keyboardController?.hide()
                     onAddItemDone()
                  }
            )
         },
         modifier =
            modifier
               .focusRequester(focusRequester)
               .fillMaxWidth()
      )
   }
}
