package com.mc.todolist

import android.app.Application
import com.mc.todolist.presentation.koin.toDoListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ToDoListApplication : Application() {
   override fun onCreate() {
      super.onCreate()
      startKoin {
         androidLogger()
         androidContext(this@ToDoListApplication)
         modules(toDoListModule)
      }
   }
}
