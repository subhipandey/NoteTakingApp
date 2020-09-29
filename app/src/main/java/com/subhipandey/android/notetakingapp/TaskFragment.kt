package com.subhipandey.android.notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import com.subhipandey.android.notetakingapp.app.navigate
import com.subhipandey.android.notetakingapp.data.Task
import com.subhipandey.android.notetakingapp.databinding.FragmentTasksBinding
import com.subhipandey.android.notetakingapp.viewmodel.TaskViewModel


class TaskFragment : Fragment(), TasksAdapter.TasksListener {
    private val viewModel by lazy {
        ViewModelProvider(this).get(TaskViewModel::class.java)
    }

    private val adapter by lazy {
        TasksAdapter(listener = this)
    }
    private lateinit var binding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentTasksBinding.inflate(inflater, container, false).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupList()

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            adapter.updateTasks(it)
        })
        binding.add.setOnClickListener {
            viewDetail()
        }


    }

    override fun deleteTaskAtPosition(task: Task, position: Int) {
        viewModel.deleteTask(task)
    }

    override fun onItemClick(task: Task) {
        viewDetail(task)
    }

    private fun viewDetail(task: Task? = null) {
        navigate(TaskFragmentDirections.viewDetails(task))
    }

    private fun setupList() {
        binding.listRV.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.listRV)
    }
}