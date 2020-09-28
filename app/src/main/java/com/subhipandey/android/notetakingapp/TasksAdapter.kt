package com.subhipandey.android.notetakingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.subhipandey.android.notetakingapp.data.Task
import com.subhipandey.android.notetakingapp.databinding.TaskItemBinding

class TasksAdapter(
    private val tasks: MutableList<Task> ,
    private val listener: TasksListener
): RecyclerView.Adapter<TasksAdapter.Companion.ViewHolder>(), ItemTouchHelpListener {

    companion object{
        class ViewHolder(val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root)
    }

    interface TasksListener{
        fun deleteTaskAtPosition(task: Task, position: Int)
        fun onItemClick(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }




    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int){

    }


}