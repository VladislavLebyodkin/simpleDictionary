package com.example.simpledictionary.noteList.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpledictionary.noteList.domain.Note

@Entity(tableName = "notes")
data class NoteEntity (
        @PrimaryKey(autoGenerate = true) var id: Long,
        @ColumnInfo(name = "word") val word: String,
        @ColumnInfo(name = "translate") val translate: String,
        @ColumnInfo(name = "example") val example: String? = "",
        @ColumnInfo(name = "createdAt") val createdAt: String,
        @ColumnInfo(name = "updatedAt") val updatedAt: String,
        @ColumnInfo(name = "boxNumber") val boxNumber: Int?,
        @ColumnInfo(name = "lastTransition") val lastTransition: String = ""
)