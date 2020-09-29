package com.subhipandey.android.notetakingapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Entity(tableName = "taskTable")
@Parcelize
data class Task(
    val title:String,
    val description: String,
    @PrimaryKey val id: String = UUID.randomUUID().toString()
): Parcelable {
    fun isContentEqual(other: Task) = title == other.title && description == other.description
}