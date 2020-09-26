package com.subhipandey.android.notetakingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.subhipandey.android.notetakingapp.app.Injection
import com.subhipandey.android.notetakingapp.data.TaskRepository

class TaskDetailsViewModel(

    private val repository: TaskRepository = Injection.roomRepository
) : ViewModel() {
}