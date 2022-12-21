package com.example.todolist

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

//extends bottom sheet dialog fragment

//pass in taskItem to constructpr - allows for edit and view mode using the same sheet
class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel
    //optional local time initialised to null
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (taskItem != null)
        {
            //when you click on a previous task, it now says edit task
            binding.taskTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            //where user types in new todo - allows for dynamic content mapping
            // this is what shows after user presses save
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.desc.text = editable.newEditable(taskItem!!.desc)
            //if both task item and duetime isnt null,
            if(taskItem!!.dueTime() != null)
            {
                dueTime = taskItem!!.dueTime()!!
                // function below
                updateTimeButtonText()
            }
        }
        //if sheet doesn;t say edit task it toggles to New task
        else {
            binding.taskTitle.text = "New Task"
        }

        //owned by 'activity' - this fragment is attached to mainActivity
        //grabbing task view model class
        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

        //event listener
        binding.saveButton.setOnClickListener {
            saveAction()
        }

        //editing and selecting time for todo event
        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if(dueTime == null)
            dueTime = LocalTime.now()
        //_, is syntax when you don't have to have defaut arg
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Task Due")
        dialog.show()

    }

    private fun updateTimeButtonText() {
        //time that shows up on sheet
        binding.timePickerButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewTaskSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction()
    {
        //assigning names to value of edittext(user input) from model
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()

        //ternary syntax - optional for user to put a due time - if they do, its rendered onscreen
        val dueTimeString = if(dueTime == null) null else TaskItem.timeFormatter.format(dueTime)
        //if its in new task mode, not edit mode
        if(taskItem == null) {

            //createing new task
            val newTask = TaskItem(name,desc, dueTimeString,null)
            //adding task to task view model model
            taskViewModel.addTaskItem(newTask)
        }
        else
        {
            taskItem!!.name = name
            taskItem!!.desc = name
            taskItem!!.dueTimeString = dueTimeString

            taskViewModel.updateTaskItem(taskItem!!)
        }

        //after submitted, it clears form
        binding.name.setText("")
        binding.desc.setText("")

        //dismisses bottom sheet fragment after submit
        dismiss()
    }

}








