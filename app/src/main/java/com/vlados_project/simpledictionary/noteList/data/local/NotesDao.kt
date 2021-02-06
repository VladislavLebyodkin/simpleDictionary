package com.vlados_project.simpledictionary.noteList.data.local

import androidx.room.*
import com.vlados_project.simpledictionary.noteList.data.local.NoteDto.Companion.COLUMN_ID
import com.vlados_project.simpledictionary.noteList.data.local.NoteDto.Companion.TABLE_NAME
import com.vlados_project.simpledictionary.noteList.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getNotesList(): Flow<List<Note>>

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

    @Transaction
    suspend fun updateAll(notes: List<NoteDto>) {
        clearTable()
        insertAll(notes)
    }

}