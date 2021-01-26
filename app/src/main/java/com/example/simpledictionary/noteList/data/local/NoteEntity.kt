package com.example.simpledictionary.noteList.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpledictionary.noteList.data.local.NoteEntity.Companion.TABLE_NAME

<<<<<<< HEAD:app/src/main/java/com/example/simpledictionary/noteList/data/local/NoteEntity.kt
@Entity(tableName = TABLE_NAME)
data class NoteEntity (
        @PrimaryKey val id: Long,
=======
@Entity(tableName = "notes")
data class NoteEntity (
        @PrimaryKey(autoGenerate = true) var id: Long,
>>>>>>> dev:app/src/main/java/com/example/simpledictionary/database/NoteDB.kt
        @ColumnInfo(name = "word") val word: String,
        @ColumnInfo(name = "translate") val translate: String,
        @ColumnInfo(name = "example") val example: String? = "",
        @ColumnInfo(name = "createdAt") val createdAt: String,
        @ColumnInfo(name = "updatedAt") val updatedAt: String,
        @ColumnInfo(name = "boxNumber") val boxNumber: Int?,
        @ColumnInfo(name = "lastTransition") val lastTransition: String = ""
) {
    companion object {
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "id"
    }
}