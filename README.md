# todoAFelonie-app
A 'todo' app implemented in Kotlin utilising a Room ORM framework backend to store data persistently.

-> Bottom sheet modal.

-> Item and description content are fragments (bottom sheet) and pops up as the keyboard.

-> Takes input from user in bottom sheet fragment and passes up to main activity (main page).


# Back-end
'Room with a view' ORM with capabilities to add, update and map completed todo items from database to app.

Ktor framework allowing for asynchronous servers.

# Front-end
Composed of fudamental Kotlin classes and interfaces: 

1) MainActivity = the root entry point to the app.

2) TaskItem = class holding a Room entity for the database structure.

3) TaskItemAdapter = holds fundamental logic for app - its constructor holds a mutble list of user-supplied todos from the 'TaskItem' class aforementioned.

Dependency to the build.gradle file in Kotlin to connect to the Room database.

Main functions are 'addTaskItem', 'updatetaskItem' and 'setCompleted' in TaskViewModel.kt.

Simple testing in jUnit.

Holds app data about if a todo item is checked or not.

# UI/UX
Simple interface in alignment with the Android colour system rule for accesibility.

Clearly highlighted areas of where user should input data and buttons to add inputs to the Room database.

Checkboxes have to be clicked in order to delete the respective items.

Strikethrough flags implemented as constants when user clicks checkbox.

Utilises 'recycler view' of nested cardViews to efficiently show large sets of data (todos) on the screen and if needed, scrollable behaviour.
