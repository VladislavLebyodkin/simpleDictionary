package com.example.simpledictionary.noteList.presentation

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private lateinit var mainAdapter: NoteListAdapter

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

        binding.btnAddNote.setOnClickListener {
            viewModel.navController.navigate(R.id.action_mainFragment_to_addNoteFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navController = findNavController()
        viewModel.onViewCreated()
        
        viewModel.notes.observe(viewLifecycleOwner) { list->
            mainAdapter.setList(list)
            if (mainAdapter.itemCount == 0) {
                binding.tvListEmpty.visibility = View.VISIBLE
            } else {
                binding.tvListEmpty.visibility = View.GONE
            }
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