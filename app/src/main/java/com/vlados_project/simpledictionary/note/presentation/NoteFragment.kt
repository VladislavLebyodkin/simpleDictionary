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

    private fun setFields(note: Note) {
        binding.run {
            inputNameEdit.setText(note.word)
            inputTranslateEdit.setText(note.translate)
            inputExampleEdit.setText(note.example)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        setHasOptionsMenu(true)
        binding = NoteFragmentBinding.inflate(layoutInflater, container, false)

        binding.btnSubmitEdit.setOnClickListener {
            if (viewModel.isValidInputName.value == true && viewModel.isValidInputTranslate.value == true ) {
                viewModel.updateNote(
                        binding.inputNameEdit.text.toString(),
                        binding.inputTranslateEdit.text.toString(),
                        binding.inputExampleEdit.text.toString(),
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

        viewModel.navController = findNavController()

        binding.inputNameEdit.doAfterTextChanged {
            viewModel.inputNameTextChanged(it.toString())
        }

        binding.inputTranslateEdit.doAfterTextChanged {
            viewModel.inputTranslateTextChanged(it.toString())
        }

        viewModel.isValidInputName.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputNameEdit.error = getString(R.string.required_field)
            }
        }

        viewModel.isValidInputTranslate.observe(viewLifecycleOwner) { isValid ->
            if (!isValid) {
                binding.inputTranslateEdit.error = getString(R.string.required_field)
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

}