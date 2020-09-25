package com.subhipandey.android.notetakingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun deleteTasks(vararg task: Task)

    @Query("SELECT * FROM taskTable ORDER BY title asc")
    fun getAllTasks(): LiveData<List<Task>?>


    
}