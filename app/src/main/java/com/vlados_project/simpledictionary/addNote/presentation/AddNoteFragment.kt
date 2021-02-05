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
                        binding.inputName.text.toString(),
                        binding.inputTranslate.text.toString(),
                        binding.inputExample.text.toString()
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

        binding.inputName.doAfterTextChanged {
            viewModel.inputNameTextChanged(it.toString())
        }

        binding.inputTranslate.doAfterTextChanged {
            viewModel.inputTranslateTextChanged(it.toString())
        }

        viewModel.isValidInputName.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputName.error = getString(R.string.required_field)
            }
        }

        viewModel.isValidInputTranslate.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputTranslate.error = getString(R.string.required_field)
            }
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
    }
}