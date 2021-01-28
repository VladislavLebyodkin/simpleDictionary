package com.example.simpledictionary.noteList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.R
import com.example.simpledictionary.noteList.domain.Note
import kotlinx.android.synthetic.main.note_item.view.*


class NoteListAdapter(
        private val onNoteClicked: (Note) -> Unit
): RecyclerView.Adapter<NoteListAdapter.MainViewHolder>() {

    private var notesList = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainViewHolder(view) {
            onNoteClicked(notesList[it])
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val note = notesList[position]
        holder.nameNote.text = note.word
        holder.translateNote.text = note.translate

        if (note.example.equals("")) {
            holder.exampleNote.text = CREATE_SENTENCE
        } else {
            holder.exampleNote.text = note.example
        }

        holder.cardNote.setOnClickListener {
            onNoteClicked(note)
        }
    }

    override fun getItemCount() = notesList.size

    class MainViewHolder(
            view: View,
            onNoteClicked: (Int) -> Unit
    ): RecyclerView.ViewHolder(view) {

        val cardNote: CardView = view.card_note
        val nameNote: TextView = view.item_note_name
        val translateNote: TextView = view.item_note_translate
        val exampleNote: TextView = view.item_note_example

        init {
            itemView.setOnClickListener {
                onNoteClicked(adapterPosition)
            }
        }
    }

    fun setList(list: List<Note>) {
        notesList = list
        notifyDataSetChanged()
    }

    companion object {
        const val CREATE_SENTENCE = "Составьте предложение"
    }
}