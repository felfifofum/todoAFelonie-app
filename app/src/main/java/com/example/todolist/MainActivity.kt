package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import java.io.IOException

//implements taskitemclicklistener interface

//before was red button - clicked to implement edittaskitem and completetask item functions below
class MainActivity : AppCompatActivity(), TaskItemClickListener
{
    // Lateinit means its a promise that it will be declared later on but will crash if you don't
    private lateinit var binding: ActivityMainBinding
    //private lateinit var taskViewModel: TaskViewModel
    private val taskViewModel: TaskViewModel by viewModels {
        //links to taskviewmodel.kt
        //this line gets database repository
        TaskItemModelFactory((application as TodoApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // inflate converts XML to its view
        binding = ActivityMainBinding.inflate(layoutInflater)
        // the entry point
        setContentView(binding.root)

        // Event listerer for clicks
        //when clicked, opens up new task sheet.kt modle
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setRecyclerView()
    }


//ORIGINAL POST REQUEST
//    val client = OkHttpClient()
//
//    val formbody: RequestBody= FormBody.Builder().add("value", "please repeat this").build()
//    val request = Request.Builder().url("http://127.0.0.1:5000/one/").post(formbody).build()
//
//
//
//    client.newCall(request).enqueue(object : Callback {
//    override fun onFailure(call: Call, e: IOException) {
//        val textView = findViewById<TextView>(R.id.textView)
//        textView.setText("hi")
//    }
//
//    override fun onResponse(call: Call,  response: Response) {
//        val textView = findViewById<TextView>(R.id.textView)
//        textView.setText("working")
//
//    }
//})






    private fun setRecyclerView() {
    // Assining MainActivity to a var
        val mainActivity = this
        taskViewModel.taskItems.observe(this){
            binding.todoListRecyclerView.apply {
                // bsic -one row -layout manager for recycler view
                layoutManager = LinearLayoutManager(applicationContext)
                //assign it to NEW task item adapter
                //passing it throught list of task items - it = index individual tasks
                adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editTaskItem(taskItem: TaskItem) {
        //edit task item, create new tsk sheet
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    override fun completeTaskItem(taskItem: TaskItem) {

        //if its complete/done
        //now after user pots todo and clicks on sheet to edit it, it is pre-populated with prior data
        taskViewModel.setCompleted(taskItem)
    }
}







