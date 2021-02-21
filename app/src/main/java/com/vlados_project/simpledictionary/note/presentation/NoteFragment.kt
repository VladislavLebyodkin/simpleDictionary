package com.vlados_project.simpledictionary.note.presentation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.databinding.NoteFragmentBinding
import com.vlados_project.simpledictionary.noteList.domain.Note
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModel {
        parametersOf(arguments?.getSerializable(NOTE_PARAMETER) as Note)
    }

    private lateinit var binding: NoteFragmentBinding

    companion object {
        const val NOTE_PARAMETER = "note"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        setHasOptionsMenu(true)
        binding = NoteFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmitEdit.setOnClickListener {
            if (viewModel.isValidInputName.value == true && viewModel.isValidInputTranslate.value == true ) {
                viewModel.updateNote(
                        binding.noteView.inputName.editText?.text.toString(),
                        binding.noteView.inputTranslate.editText?.text.toString(),
                        binding.noteView.inputExample.editText?.text.toString(),
                )
            } else {
                Toast.makeText(context, getString(R.string.fill_all_required_fields), Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiModel.observe(viewLifecycleOwner) { note ->
            setFields(note)
        }

        startValidation()

        viewModel.navController = findNavController()

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_delete_note -> {
                viewModel.deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFields(note: Note) {
        binding.noteView.run {
            inputName.editText?.setText(note.word)
            inputTranslate.editText?.setText(note.translate)
            inputExample.editText?.setText(note.example)
        }
    }

    private fun startValidation() {
        viewModel.inputNameTextChanged(binding.noteView.inputName.editText?.text.toString())
        viewModel.inputTranslateTextChanged(binding.noteView.inputTranslate.editText?.text.toString())
    }

}