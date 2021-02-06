package com.vlados_project.simpledictionary.noteList.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.noteList.domain.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteListAdapter(
        private val onNoteClicked: (Note) -> Unit
) : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NotesDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view) {
            onNoteClicked(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.nameNote.text = note.word
        holder.translateNote.text = note.translate

        if (!note.example.isNullOrEmpty()) {
            holder.exampleNote.text = note.example
        }
    }

    class NoteViewHolder(
            view: View,
            onNoteClicked: (Int) -> Unit
    ): RecyclerView.ViewHolder(view) {

        val nameNote: TextView = view.item_note_name
        val translateNote: TextView = view.item_note_translate
        val exampleNote: TextView = view.item_note_example

        init {
            itemView.setOnClickListener {
                onNoteClicked(adapterPosition)
            }
        }
    }

    class NotesDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}

