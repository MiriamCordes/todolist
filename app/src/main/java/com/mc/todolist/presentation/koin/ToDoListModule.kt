package com.mc.todolist.presentation.koin

import androidx.room.Room
import com.mc.todolist.data.database.ToDoListDao
import com.mc.todolist.data.database.ToDoListDatabase
import com.mc.todolist.presentation.donelist.DoneListViewModel
import com.mc.todolist.presentation.todolist.ToDoListViewModel
import com.mc.todolist.presentation.todolistitem.ToDoListItemViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val toDoListModule =
   module {
      single {
         Room
            .databaseBuilder(
               androidContext(),
               ToDoListDatabase::class.java,
               "toDoListDatabase"
            )
            .fallbackToDestructiveMigration()
            .build()
      }

      single<ToDoListDao> {
         val database = get<ToDoListDatabase>()
         database.toDoListDao()
      }

      viewModel { ToDoListViewModel() }
      viewModel { ToDoListItemViewModel() }
      viewModel { DoneListViewModel() }
   }
