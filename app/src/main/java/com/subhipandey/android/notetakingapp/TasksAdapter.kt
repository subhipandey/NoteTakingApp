package com.subhipandey.android.notetakingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.subhipandey.android.notetakingapp.data.Task
import com.subhipandey.android.notetakingapp.data.TaskDiffCallback
import com.subhipandey.android.notetakingapp.databinding.TaskItemBinding

class TasksAdapter(
    private val tasks: MutableList<Task> = mutableListOf(),
    private val listener: TasksListener
) : RecyclerView.Adapter<TasksAdapter.Companion.ViewHolder>(), ItemTouchHelpListener {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.title = tasks[position].title
        holder.binding.root.setOnClickListener {
            listener.onItemClick(task)
        }
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = tasks.size


    override fun onItemDismiss(viewHolder: RecyclerView.ViewHolder, position: Int) {
        listener.deleteTaskAtPosition(task = tasks[position], position = position)
    }

    fun updateTasks(tasks: List<Task>?) =
        tasks?.let{
            val diffCallback = TaskDiffCallback(this.tasks, tasks)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            this.tasks.clear()
            this.tasks.addAll(tasks)
            diffResult.dispatchUpdatesTo(this)
        }


    companion object {
        class ViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)
    }



    interface TasksListener {
        fun deleteTaskAtPosition(task: Task, position: Int)
        fun onItemClick(task: Task)
    }


}