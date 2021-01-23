package com.example.simpledictionary.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteDB::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDAO: NotesDAO

}