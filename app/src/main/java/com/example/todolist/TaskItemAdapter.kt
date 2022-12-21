package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.TaskItemCellBinding

//for setRecycler view in MainActivity
//adapters allow to map an interface to the target class - alternative to inheritance
class TaskItemAdapter(
    //it recieves both taskItem class and button click listener
    private val taskItems: List<TaskItem>,
    //copied from taskitemviewholder
    private val clickListener: TaskItemClickListener
    //its type os recycler`view adapter
): RecyclerView.Adapter<TaskItemViewHolder>() {
    //was error message - implement members and auto populates with 3 predefined functions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        // creating var from and assigning it resources of the parent view group
        val from = LayoutInflater.from(parent.context)
        //puts together task item class features and converts 3 params form XML
        //false = attach child view (individual item) to parent view (mainActivity) later
        val binding = TaskItemCellBinding.inflate(from, parent, false)
        //context object = contains metadata about items in the recyclerview
        return TaskItemViewHolder(parent.context, binding, clickListener)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        //using list to get specific task item - when user clicks edit
        holder.bindTaskItem(taskItems[position])
    }

    // efficient to return the size of the taskItem
    override fun getItemCount(): Int = taskItems.size

}

