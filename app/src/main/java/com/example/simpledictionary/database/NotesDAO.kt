package com.example.simpledictionary.database

import androidx.room.*
import com.example.simpledictionary.noteList.domain.Note

@Dao
interface NotesDAO {

    @Query("SELECT * FROM notes")
    suspend fun getNotesList(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: NoteDB)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(note: NoteDB)

    @Query("DELETE FROM notes")
    suspend fun clearTable()

}