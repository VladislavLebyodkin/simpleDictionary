package com.example.simpledictionary.noteList.presentation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.NoteListFragmentBinding
import com.example.simpledictionary.util.log
import org.koin.android.viewmodel.ext.android.viewModel

class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModel()
    private lateinit var binding: NoteListFragmentBinding
    private lateinit var mainAdapter: NoteListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        setHasOptionsMenu(true)
        binding = NoteListFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navController = findNavController()

        viewModel.onViewCreated()

        mainAdapter = NoteListAdapter { note ->
            viewModel.onNoteClick(note)
        }

        binding.recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        binding.btnAddNote.setOnClickListener {
            viewModel.onAddNoteClick()
        }

        viewModel.notes.observe(viewLifecycleOwner) { list->
            mainAdapter.setList(list)

            binding.tvListEmpty.isVisible = mainAdapter.itemCount == 0
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }
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