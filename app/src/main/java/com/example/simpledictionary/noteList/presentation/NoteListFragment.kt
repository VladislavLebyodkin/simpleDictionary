package com.example.simpledictionary.noteList.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.MainFragmentBinding
import com.example.simpledictionary.noteList.domain.Note
import org.koin.android.viewmodel.ext.android.viewModel

class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private lateinit var mainAdapter: NoteListAdapter

    private val observer = Observer<List<Note>> {
        mainAdapter.setList(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        mainAdapter = NoteListAdapter { note ->
            viewModel.onNoteClick(note)
        }

        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        viewModel.notes.observe(viewLifecycleOwner, observer)

        binding.btnAddNote.setOnClickListener {
            viewModel.navController.navigate(R.id.action_mainFragment_to_addNoteFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navController = findNavController()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_shuffle -> {
                viewModel.onShuffleClick()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}