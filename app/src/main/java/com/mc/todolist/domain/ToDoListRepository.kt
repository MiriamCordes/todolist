package com.mc.todolist.domain

interface ToDoListRepository {
   suspend fun getToDoList()

   suspend fun addToDoListItem()
}
