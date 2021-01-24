package com.example.simpledictionary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpledictionary.noteList.data.local.NoteEntity
import com.example.simpledictionary.noteList.data.local.NotesDao

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDAO: NotesDao

}