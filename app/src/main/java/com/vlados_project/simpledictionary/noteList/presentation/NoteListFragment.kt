package com.vlados_project.simpledictionary.noteList.presentation

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.databinding.NoteListFragmentBinding
import com.vlados_project.simpledictionary.util.log
import kotlinx.android.synthetic.main.note_list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class NoteListFragment : Fragment() {

    private val viewModel: NoteListViewModel by viewModel()
    private lateinit var binding: NoteListFragmentBinding
    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        setHasOptionsMenu(true)
        binding = NoteListFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navController = findNavController()

        hideKeyboardFrom(context, view)

        noteListAdapter = NoteListAdapter { note ->
            viewModel.onNoteClick(note)
        }

        val linearLayoutManager = LinearLayoutManager(this.context)

        binding.recyclerView.apply {
            adapter = noteListAdapter
            layoutManager = linearLayoutManager
        }

        binding.btnAddNote.setOnClickListener {
            viewModel.onAddNoteClick()
        }

        viewModel.notes.observe(viewLifecycleOwner) { list ->
            log(list)
            noteListAdapter.submitList(list)

            binding.tvListEmpty.isVisible = list.isEmpty()
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.smth_wrong), Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount
                val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()

                viewModel.onScrolled(totalCount, lastVisibleItemPosition)

                if(dy > 0 && binding.btnAddNote.visibility == View.VISIBLE) {
                    binding.btnAddNote.hide()
                } else if (dy < 0 && binding.btnAddNote.visibility != View.VISIBLE) {
                    binding.btnAddNote.show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btn_logout -> {
                viewModel.onLogoutClick()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideKeyboardFrom(context: Context?, view: View) {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}