package com.subhipandey.android.notetakingapp.app

import android.app.Application
import com.subhipandey.android.notetakingapp.data.TaskDatabase

class TaskApplication : Application() {
    companion object {

        lateinit var database: TaskDatabase
    }

    override fun onCreate() {

        super.onCreate()
        database = TaskDatabase.getDatabase(this)
    }
}