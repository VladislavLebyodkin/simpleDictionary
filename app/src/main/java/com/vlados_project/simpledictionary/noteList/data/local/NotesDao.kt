package com.vlados_project.simpledictionary.noteList.data.local

import androidx.room.*
import com.vlados_project.simpledictionary.noteList.data.local.NoteDto.Companion.COLUMN_ID
import com.vlados_project.simpledictionary.noteList.data.local.NoteDto.Companion.TABLE_NAME
import com.vlados_project.simpledictionary.noteList.domain.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getNotesList(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: NoteDto)

    @Query("DELETE FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(note: NoteDto)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearTable()

}