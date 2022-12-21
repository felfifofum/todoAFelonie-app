package com.example.todolist

interface TaskItemClickListener {

    //contains 2 function
    //links to constructor of TaskItemViewHolder
    //when you click on card view
    fun editTaskItem(taskItem: TaskItem)

    //when you click the complete button
    fun completeTaskItem(taskItem: TaskItem)
}