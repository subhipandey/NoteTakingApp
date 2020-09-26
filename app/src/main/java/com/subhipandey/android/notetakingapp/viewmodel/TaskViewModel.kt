package com.subhipandey.android.notetakingapp.viewmodel

import androidx.lifecycle.ViewModel
import com.subhipandey.android.notetakingapp.app.Injection
import com.subhipandey.android.notetakingapp.data.RoomRepository
import com.subhipandey.android.notetakingapp.data.TaskRepository

class TaskViewModel(
    private val repository: TaskRepository =Injection.roomRepository
): ViewModel() {
}