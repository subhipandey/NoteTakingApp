package com.subhipandey.android.notetakingapp.viewmodel

import androidx.lifecycle.*
import com.subhipandey.android.notetakingapp.app.Injection
import com.subhipandey.android.notetakingapp.data.RoomRepository
import com.subhipandey.android.notetakingapp.data.Task
import com.subhipandey.android.notetakingapp.data.TaskRepository
import com.subhipandey.android.notetakingapp.data.WorkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(
    private val repository: TaskRepository =Injection.roomRepository
): ViewModel() {
    val tasks: LiveData<List<Task>?> = repository.getTasks()

    private val _isEmpty = MediatorLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    private val _workState = MutableLiveData<WorkState>()
    val workState: LiveData<WorkState>
    get() = _workState

    init{
        _isEmpty.addSource(tasks){
            _isEmpty.postValue(it?.isNullOrEmpty() ?: true)
        }
    }

    fun deleteTask(task: Task){
        _workState.value = WorkState.LOADING
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteTask(task)
            _workState.postValue(WorkState.FINISH)
        }
    }
}