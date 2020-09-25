package com.subhipandey.android.notetakingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "taskTable")
data class Task(
    val title:String,
    val description: String,
    @PrimaryKey val id: String = UUID.randomUUID().toString()
)