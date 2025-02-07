package com.subhipandey.android.notetakingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Task::class)], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        private const val databaseName = "task_database"

        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
               return  Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    databaseName
                ).build().apply {
                    INSTANCE = this
                }
            }
        }

    }

}