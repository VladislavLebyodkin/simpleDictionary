package com.example.simpledictionary.noteList.data.local

import androidx.room.*
import com.example.simpledictionary.noteList.domain.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM ${NoteEntity.TABLE_NAME}")
    suspend fun getNotesList(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: NoteEntity)

    @Query("DELETE FROM ${NoteEntity.TABLE_NAME} WHERE ${NoteEntity.COLUMN_ID} = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("DELETE FROM ${NoteEntity.TABLE_NAME}")
    suspend fun clearTable()

}