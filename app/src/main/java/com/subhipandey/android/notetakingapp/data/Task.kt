package com.subhipandey.android.notetakingapp.data

import java.util.UUID

data class Task(
    val title:String,
    val description: String,
    val id: String = UUID.randomUUID().toString()
)