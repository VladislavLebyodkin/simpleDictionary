package com.example.simpledictionary.addNote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.addNote.data.NoteAddDto
import com.example.simpledictionary.databinding.AddNoteFragmentBinding
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment() {

    private val addNoteViewModel: AddNoteViewModel by viewModel()
    private lateinit var binding: AddNoteFragmentBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: AddNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = AddNoteFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmit.setOnClickListener {
            addNoteViewModel.createNote(
                    binding.inputName.text.toString(),
                    binding.inputTranslate.text.toString(),
                    binding.inputExample.text.toString()
            )
            navController.navigate(R.id.action_addNoteFragment_to_mainFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }


}