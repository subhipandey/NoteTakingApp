package com.subhipandey.android.notetakingapp.viewmodel

import androidx.lifecycle.*
import com.subhipandey.android.notetakingapp.app.Injection
import com.subhipandey.android.notetakingapp.data.Task
import com.subhipandey.android.notetakingapp.data.TaskRepository
import com.subhipandey.android.notetakingapp.data.WorkState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskDetailsViewModel(

    private val repository: TaskRepository = Injection.roomRepository
) : ViewModel() {
    val description = MediatorLiveData<String>()
    val title = MediatorLiveData<String>()
    val canSave: LiveData<Boolean>
    get() = _canSave

    val workState: LiveData<WorkState>
    get() = _workState

    val showLoading: LiveData<Boolean>
    get() = Transformations.map(workState) {
        it == WorkState.LOADING
    }

    private val _canSave = MediatorLiveData<Boolean>()
    private val _task = MutableLiveData<Task>()
    val task: LiveData<Task>
    get() = _task
    private val _workState = MutableLiveData<WorkState>()

    init {
        _workState.value = WorkState.INITIAL
        title.addSource(task) { task ->
            task?.let {
                title.value = task.title
            }
        }

        description.addSource(task) { task ->
            task?.let {
                description.value = task.description
            }
        }
        _canSave.addSource(title) {
            _canSave.value = checkCanSave()
        }
        _canSave.addSource(description) {
            _canSave.value = checkCanSave()
        }
    }
        fun setTask(task: Task?){
            if(_task.value != task){
                _task.value = task
            }
        }
        fun saveAction(){
            val task = _task.value
            title.value?.let {title ->
                description.value?.let {description ->
                    if (task!= null){
                        task.copy(title = title, description = description).run{
                            updateTask(task = this)
                        }
                    }else {
                        Task(title = title, description = description).run {
                            saveTask(task = this)
                        }
                    }
                }
            }
        }

    private fun checkCanSave(): Boolean {
        val title = title.value
        val description = description.value
        return (title?.isEmpty() == false && description?.isEmpty() == false && (title != _task.value?.title || description != _task.value?.description))
    }

        private fun saveTask(task: Task){
            _workState.value = WorkState.LOADING
            _task.value = task
            viewModelScope.launch(Dispatchers.Default) {
                repository.addTask(task)
                withContext(Dispatchers.Main){
                    _workState.value = WorkState.FINISH
                }
            }
        }
        private fun updateTask(task: Task){
            _workState.value = WorkState.LOADING
            _task.value = task
            viewModelScope.launch(Dispatchers.Default){
                repository.updateTask(task)
                withContext(Dispatchers.Main){
                    _workState.value = WorkState.FINISH
                }
            }
        }
    }
