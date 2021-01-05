package com.example.simpledictionary.notes.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.R
import com.example.simpledictionary.databinding.MainFragmentBinding
import com.example.simpledictionary.notes.data.NotesDto
import com.example.simpledictionary.notes.domain.Note
import com.example.simpledictionary.util.log
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var recyclerView: RecyclerView

    private val observer = Observer<List<Note>> {
        mainAdapter.setList(it)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        mainAdapter = MainAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = mainAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        mainViewModel.notes.observe(this.viewLifecycleOwner, observer)

        return binding.root
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