package com.example.todolist

import androidx.lifecycle.*
import com.example.todolist.TaskItem.Companion.dateFormatter
import kotlinx.coroutines.launch
import java.time.LocalDate

// extents view model

//to get list of datk items below, needs access to repository from  file
class TaskViewModel(private val repository: TaskItemRepository): ViewModel()
{
    //its type is the class TaskItem i.e. string
    //live data class holds data through being lifecycle aware - aware of other components existance
    val taskItems: LiveData<List<TaskItem>> = repository.allTaskItems.asLiveData()

    fun addTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        //insert the task item directly form room db
        repository.insertTaskItem(taskItem)
    }

    fun updateTaskItem(taskItem: TaskItem) = viewModelScope.launch {
        //reinstates edited task in db
        repository.updateTaskItem(taskItem)
    }

    fun setCompleted(taskItem: TaskItem) = viewModelScope.launch {
        if (!taskItem.isCompleted())
            // keeps date the same if not checked
            taskItem.completedDateString = TaskItem.dateFormatter.format(LocalDate.now())
        repository.updateTaskItem(taskItem)
    }
}


class TaskItemModelFactory(private val repository: TaskItemRepository) : ViewModelProvider.Factory {
    //overrides create function
    // T param = another word for Type
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //read as english
        if (modelClass.isAssignableFrom(TaskViewModel::class.java))
            return TaskViewModel(repository) as T

        // if its not
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}