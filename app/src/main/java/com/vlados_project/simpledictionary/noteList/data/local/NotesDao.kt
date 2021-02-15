package com.vlados_project.simpledictionary.noteList.data.local

import androidx.room.*
import com.vlados_project.simpledictionary.noteList.data.local.NoteEntity.Companion.COLUMN_ID
import com.vlados_project.simpledictionary.noteList.data.local.NoteEntity.Companion.TABLE_NAME
import com.vlados_project.simpledictionary.noteList.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY id")
    fun getNotesListAsFlow(): Flow<List<Note>>

    @Query("SELECT * FROM $TABLE_NAME")
    fun getNotesList(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<NoteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Query("DELETE FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun clearTable()

}