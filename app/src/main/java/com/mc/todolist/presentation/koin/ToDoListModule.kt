package com.mc.todolist.presentation.koin

import com.mc.todolist.presentation.donelist.DoneListViewModel
import com.mc.todolist.presentation.todolist.ToDoListViewModel
import com.mc.todolist.presentation.todolistitem.ToDoListItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val toDoListModule =
   module {
      viewModel { ToDoListViewModel() }
      viewModel { ToDoListItemViewModel() }
      viewModel { DoneListViewModel() }
   }
