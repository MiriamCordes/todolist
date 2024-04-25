package com.mc.todolist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDoListItems")
data class ToDoListItemEntity(
   @PrimaryKey
   val id: String,
   val label: String,
   val checked: Boolean
)
