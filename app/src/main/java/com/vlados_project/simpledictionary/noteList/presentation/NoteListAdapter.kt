package com.vlados_project.simpledictionary.noteList.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vlados_project.simpledictionary.R
import com.vlados_project.simpledictionary.noteList.domain.Note

class NoteListAdapter(
        private val onNoteClicked: (Note) -> Unit
) : ListAdapter<Note, NoteViewHolder>(NotesDiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: NoteViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener {
            onNoteClicked(holder.currentNote)
        }
    }

    override fun onViewDetachedFromWindow(holder: NoteViewHolder) {
        super.onViewDetachedFromWindow(holder)

        holder.itemView.setOnClickListener(null)
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

