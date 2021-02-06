package com.vlados_project.simpledictionary.noteList.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vlados_project.simpledictionary.noteList.domain.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteViewHolder(
    view: View
): RecyclerView.ViewHolder(view) {

    lateinit var currentNote: Note

    private val nameNote: TextView = view.item_note_name
    private val translateNote: TextView = view.item_note_translate
    private val exampleNote: TextView = view.item_note_example

    fun bind(note: Note) {
        currentNote = note

        nameNote.text = note.word
        translateNote.text = note.translate

        if (!note.example.isNullOrEmpty()) {
            exampleNote.text = note.example
        }
    }
}