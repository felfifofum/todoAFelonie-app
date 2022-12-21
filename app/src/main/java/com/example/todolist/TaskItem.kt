package com.example.todolist

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

//room entity
//entity with tablename taskitem table
@Entity(tableName = "task_item_table")
class TaskItem(
    //DATABASE STRUCTURE
    //attributes
    //Similar structure to
    //giving each attr column names for db
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "desc") var desc: String,
    //Optional - it can be null without throwing errors
    @ColumnInfo(name = "dueTimeString") var dueTimeString: String?, // initially tored as string, then converted to local time
    @ColumnInfo(name = "completedDateString") var completedDateString: String?,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
{

    //is user input for time section is null but nothing into local date, else pass into the string the user supplied date
    private fun completedDate(): LocalDate? = if (completedDateString == null) null else LocalDate.parse(completedDateString, dateFormatter)
    //same as above
    fun dueTime(): LocalTime? = if (dueTimeString == null) null else LocalTime.parse(dueTimeString, timeFormatter)

    //links with taskitemviewholder.kt - shorthand function
    // links to taskitemviewholder.kt
    fun isCompleted() = completedDate() != null
    //gives it corresponding image for button in action - ternary
    fun imageResource(): Int = if(isCompleted()) R.drawable.checked_24 else R.drawable.unchecked_24
    //makes circle purple if clicked - aesthetics
    fun imageColor(context: Context): Int = if(isCompleted()) purple(context) else black(context)

    //function to actually implement the colour in the imageColor ternary
    private fun purple(context: Context) = ContextCompat.getColor(context, R.color.purple_500)
    private fun black(context: Context) = ContextCompat.getColor(context, R.color.black)

        //has a time and date formatter to use uniform formatting accross classes - ISO lib
    companion object {
            //time and date formatters used above in completedDate func
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME //2022-07-15
        val dateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    }
}