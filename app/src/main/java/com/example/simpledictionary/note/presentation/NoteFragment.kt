package com.example.simpledictionary.note.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.NoteFragmentBinding
import com.example.simpledictionary.noteList.domain.Note
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModel {
        parametersOf(arguments?.getSerializable(NOTE_PARAMETER) as Note)
    }
    private lateinit var note: Note
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
            viewModel.updateNote(
                    binding.inputNameEdit.text.toString(),
                    binding.inputTranslateEdit.text.toString(),
                    binding.inputExampleEdit.text.toString(),
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.uiModel.observe(viewLifecycleOwner) { note ->
            setFields(note)
        }

        viewModel.navController = findNavController()
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