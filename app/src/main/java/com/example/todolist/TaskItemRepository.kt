package com.example.todolist

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

//dao value - dat access obj
class TaskItemRepository(private val taskItemDao: TaskItemDao) {
    //recieve taskitemdao
    val allTaskItems: Flow<List<TaskItem>> = taskItemDao.allTaskItems()

    //threading in kotlin coroutines for async nature
    @WorkerThread
    suspend fun insertTaskItem(taskItem: TaskItem)
    {
        taskItemDao.insertTaskItem(taskItem)
    }

    @WorkerThread
    suspend fun updateTaskItem(taskItem: TaskItem) {
        //recieving task item and updating in in dao
        taskItemDao.updateTaskItem(taskItem)
    }
}