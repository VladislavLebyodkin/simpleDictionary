package com.example.simpledictionary.noteList.data.local

import androidx.room.*
import com.example.simpledictionary.noteList.data.local.NoteEntity
import com.example.simpledictionary.noteList.domain.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    suspend fun getNotesList(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("DELETE FROM notes")
    suspend fun clearTable()

}