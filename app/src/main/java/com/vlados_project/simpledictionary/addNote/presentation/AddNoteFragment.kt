package com.vlados_project.simpledictionary.addNote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.databinding.AddNoteFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AddNoteFragment : Fragment() {

    private val viewModel: AddNoteViewModel by viewModel()
    private lateinit var binding: AddNoteFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = AddNoteFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmit.setOnClickListener {
            if (viewModel.isValidInputName.value == true && viewModel.isValidInputTranslate.value == true) {
                viewModel.createNote(
                        binding.noteView.inputName.editText?.text.toString(),
                        binding.noteView.inputTranslate.editText?.text.toString(),
                        binding.noteView.inputExample.editText?.text.toString()
                )
            } else {
                Toast.makeText(context, getString(R.string.fill_all_required_fields), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navController = findNavController()

        startValidation()

        binding.noteView.inputName.editText?.doAfterTextChanged {
            viewModel.inputNameTextChanged(it.toString())
        }

        binding.noteView.inputTranslate.editText?.doAfterTextChanged {
            viewModel.inputTranslateTextChanged(it.toString())
        }

        viewModel.isValidInputName.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.noteView.inputName.error = getString(R.string.required_field)
            } else {
                binding.noteView.inputName.error = null
            }
        }

        viewModel.isValidInputTranslate.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.noteView.inputTranslate.error = getString(R.string.required_field)
            } else {
                binding.noteView.inputTranslate.error = null
            }
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
    }

    private fun startValidation() {
        viewModel.inputNameTextChanged(binding.noteView.inputName.editText?.text.toString())
        viewModel.inputTranslateTextChanged(binding.noteView.inputTranslate.editText?.text.toString())
    }
}