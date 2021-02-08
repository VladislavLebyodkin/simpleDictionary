package com.vlados_project.simpledictionary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vlados_project.simpledictionary.noteList.data.local.NoteEntity
import com.vlados_project.simpledictionary.noteList.data.local.NotesDao

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDao: NotesDao

}