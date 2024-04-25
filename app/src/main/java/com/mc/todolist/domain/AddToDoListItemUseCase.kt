package com.mc.todolist.domain

class AddToDoListItemUseCase(
   private val repository: ToDoListRepository
) {
   suspend fun execute() = repository.addToDoListItem()
}
