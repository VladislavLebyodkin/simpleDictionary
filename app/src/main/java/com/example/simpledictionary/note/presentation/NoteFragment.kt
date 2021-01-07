package com.example.simpledictionary.note.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.NoteFragmentBinding
import com.example.simpledictionary.noteList.domain.Note
import org.koin.android.viewmodel.ext.android.viewModel

class NoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModel()
    private lateinit var note: Note
    private lateinit var binding: NoteFragmentBinding
    private lateinit var navController: NavController

    private fun setFields(note: Note) {
        binding.let {
            it.inputNameEdit.setText(note.word)
            it.inputTranslateEdit.setText(note.translate)
            it.inputExampleEdit.setText(note.example)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = NoteFragmentBinding.inflate(layoutInflater, container, false)

        val note = arguments?.getSerializable("note") as Note

        setFields(note)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_delete_note -> {
                noteViewModel.deleteNote(note.id)
                navController.navigate(R.id.action_noteFragment_to_mainFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}