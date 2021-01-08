package com.example.simpledictionary.noteList.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.MainFragmentBinding
import com.example.simpledictionary.noteList.domain.Note
import org.koin.android.viewmodel.ext.android.viewModel

class NoteListFragment : Fragment() {

    private val mainViewModel: NoteListViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private lateinit var mainAdapter: NoteListAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var navController: NavController

    private val observer = Observer<List<Note>> {
        mainAdapter.setList(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        mainAdapter = NoteListAdapter { note ->
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
        recyclerView = binding.recyclerView
        recyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this.context)
        }

        mainViewModel.notes.observe(this.viewLifecycleOwner, observer)

        binding.btnAddNote.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_addNoteFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_shuffle -> {
                mainAdapter.shuffle()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}