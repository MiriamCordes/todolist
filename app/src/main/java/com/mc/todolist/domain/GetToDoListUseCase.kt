package com.mc.todolist.domain

class GetToDoListUseCase(
   private val repository: ToDoListRepository
) {
   suspend fun execute() = repository.getToDoList()
}
