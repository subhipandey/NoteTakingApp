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
import com.subhipandey.android.notetakingapp.TaskDetailsFragmentArgs.Companion.fromBundle
import com.subhipandey.android.notetakingapp.app.hideKeyboard
import com.subhipandey.android.notetakingapp.data.WorkState
import com.subhipandey.android.notetakingapp.databinding.FragmentTaskDetailsBinding
import com.subhipandey.android.notetakingapp.viewmodel.TaskDetailsViewModel


class TaskDetailsFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(TaskDetailsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = FragmentTaskDetailsBinding.inflate(inflater, container, false ).also {
        it.lifecycleOwner = this
        it.viewModel = this.viewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            viewModel.setTask(fromBundle(bundle).task)
        }

        viewModel.workState.observe(viewLifecycleOwner, Observer {
            if (it == WorkState.LOADING){
                view.hideKeyboard()
            }
            if (it == WorkState.FINISH){
                findNavController().navigateUp()
            }
        })


    }
}