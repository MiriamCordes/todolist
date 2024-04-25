package com.mc.todolist.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ToDoListDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertToDoListItem(toDoListItemEntity: ToDoListItemEntity)

   @Query("SELECT * FROM todolistItems")
   suspend fun getToDoListItems(): List<ToDoListItemEntity>?
}
