package com.example.todolist

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskItemCellBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    //what it recieves in constructor
    private val context: Context,
    private val binding: TaskItemCellBinding,
    //links to taskitemclicklistener.kt - needs it passed through in constructor
    //copied into taksitemadapter as it is caputed through main activity
    private val clickListener: TaskItemClickListener
    //its type
    //constructor of viewholder - what is displayed on screen
): RecyclerView.ViewHolder(binding.root) {
    // structure to hold the user's inputted time on-screen on the right-uses libs
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    // recieves a task item
    fun bindTaskItem(taskItem: TaskItem) {
        // shows up on screen
        binding.name.text = taskItem.name

        // strike through
        //links to taskitem.kt
        if (taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        //tick or empty circle
        binding.completeButton.setImageResource(taskItem.imageResource())
        //certain elements require context inn the documentation
        binding.completeButton.setColorFilter(taskItem.imageColor(context))


        // not TaskItemClickListener is passed in constructor, it calls when the interface shows
        //links to taskitemclicklistener
        binding.completeButton.setOnClickListener{
            clickListener.completeTaskItem(taskItem)
        }

        //when  user clicks to edit on card mode, goes into editview
        binding.taskCellContainer.setOnClickListener{
            clickListener.editTaskItem(taskItem)
        }

        //it user has opted in for a due time
        if(taskItem.dueTime() != null)
            //var declared above
            binding.dueTime.text = timeFormat.format(taskItem.dueTime())
        else
            //shows no time
            binding.dueTime.text = ""
    }
}