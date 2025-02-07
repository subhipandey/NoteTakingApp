package com.subhipandey.android.notetakingapp.data

import androidx.lifecycle.LiveData

interface TaskRepository {
    suspend fun addTask(task: Task)
    fun getTasks(): LiveData<List<Task>?>
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task : Task)
}
