package com.vlados_project.simpledictionary.noteList.domain

import java.io.Serializable

data class Note(
        val id: Long,
        val word: String,
        val translate: String,
        val example: String? = ""
): Serializable {
        override fun toString(): String {
                return this.id.toString()
        }
}
