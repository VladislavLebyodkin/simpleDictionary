package com.example.simpledictionary.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.databinding.MainFragmentBinding
import com.example.simpledictionary.model.Resp
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: MainFragmentBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var recyclerView: RecyclerView


    private val observer = Observer<Resp> {
        Log.d("TAG", it.notes.toString())
        mainAdapter.setList(it.notes)
    }

    private lateinit var observerList: Observer<Resp>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)

        mainAdapter = MainAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = mainAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        mainViewModel.notes.observe(this.viewLifecycleOwner, observer)

        return binding.root
    }

}