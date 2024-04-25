package com.mc.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoListItemEntity::class], version = 1)
abstract class ToDoListDatabase : RoomDatabase() {
   abstract fun toDoListDao(): ToDoListDao
}
