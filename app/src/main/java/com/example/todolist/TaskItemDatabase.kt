package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//type = room database
//first version
//:: creates reference to class

// array of classes
@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
public abstract class TaskItemDatabase : RoomDatabase() {

    //within db, build anstract function
    abstract fun taskItemDao(): TaskItemDao

    //here companion obj is the instance of db, instead of working with whole thing

    //comp objs = how static variables and methods are defined
    companion object {
        @Volatile
        //initialised as null and volatile
        private var INSTANCE: TaskItemDatabase? = null

        fun getDatabase(context: Context): TaskItemDatabase
        {
            // use of documentation
            // if instnce is already being initialised, return it, otherwise initialise instance od db in bg
            return INSTANCE ?: synchronized(this)
                {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        //type of database to create
                        TaskItemDatabase::class.java,
                        "task_item_database"
                    ).build()
                    INSTANCE = instance
                    instance
                }
        }
    }
}