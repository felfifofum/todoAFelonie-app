package com.example.todolist

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// Data access object - gives methods so rest of todo app cn interact with data in table

@Dao
//name of local db
interface TaskItemDao

// array of entities with all info from db
{
    //sql query, ordered by id
    @Query("SELECT * FROM task_item_table ORDER BY id ASC")
    // flow of task item - recieves live updates from the database + emits multiple values asynchronously
    fun allTaskItems(): Flow<List<TaskItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend is async
    //inserts new task item into databasr
    suspend fun insertTaskItem(taskItem: TaskItem)

    @Update
    suspend fun updateTaskItem(taskItem: TaskItem)

    @Delete
    //for suture use
    suspend fun deleteTaskItem(taskItem: TaskItem)
}