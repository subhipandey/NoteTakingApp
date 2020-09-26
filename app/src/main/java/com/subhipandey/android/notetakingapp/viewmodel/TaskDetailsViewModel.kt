package com.subhipandey.android.notetakingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhipandey.android.notetakingapp.app.Injection
import com.subhipandey.android.notetakingapp.data.Task
import com.subhipandey.android.notetakingapp.data.TaskRepository
import kotlinx.coroutines.launch

class TaskDetailsViewModel(

    private val repository: TaskRepository = Injection.roomRepository
) : ViewModel() {
    val tasks: LiveData<List<Task>?> = repository.getTasks()

    private val _isEmpty = MediatorLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        _isEmpty.addSource(tasks){
            _isEmpty.postValue(it?.isNullOrEmpty() ?: true)
        }
    }

    fun deleteTask(task : Task){
        viewModelScope.launch{
            repository.deleteTask(task)
        }
    }
}