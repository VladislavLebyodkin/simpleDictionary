package com.example.simpledictionary.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.R
import com.example.simpledictionary.model.Note


class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var notesList = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.d("TAG", "OnBind")
        holder.nameNote.text = notesList[position].word
        holder.translateNote.text = notesList[position].translate
        holder.exampleNote.text = notesList[position].example
    }

    override fun getItemCount() = notesList.size

    class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val translateNote: TextView = view.findViewById(R.id.item_note_translate)
        val exampleNote: TextView = view.findViewById(R.id.item_note_example)
    }

    fun setList(list: List<Note>) {
        notesList = list
        notifyDataSetChanged()
    }
}