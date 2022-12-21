package com.example.todolist

import android.app.Application

//extends application
class TodoApplication : Application() {
    //lazy = kotlin method to crete an object inside a class
    //create instance od db by calling lazy
    private val database by lazy { TaskItemDatabase.getDatabase(this) }
    //initialise repo by lazy - in constructor is dao
    val repository by lazy { TaskItemRepository(database.taskItemDao()) }

}