package com.subhipandey.android.notetakingapp.data

import androidx.lifecycle.LiveData
import com.subhipandey.android.notetakingapp.app.TaskApplication

class RoomRepository: TaskRepository {
private val taskDao: TaskDao = TaskApplication.database.taskDao()
    private val allTasks: LiveData<List<Task>?>

    init{
        allTasks = taskDao.getAllTasks()
    }
    override suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    override fun getTasks(): LiveData<List<Task>?> = allTasks

    override suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTasks(task)
    }
}