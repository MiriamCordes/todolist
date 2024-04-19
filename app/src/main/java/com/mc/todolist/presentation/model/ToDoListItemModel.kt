package com.mc.todolist.presentation.model

data class ToDoListItemModel(
   val id: Int,
   val label: String,
   val checked: Boolean = false
)
