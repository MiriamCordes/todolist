package com.mc.todolist.domain

import com.mc.todolist.presentation.model.ToDoListItemModel

class AddToDoListItemUseCase(
   private val repository: ToDoListRepository
) {

   suspend fun execute() = repository.addToDoListItem()
}
