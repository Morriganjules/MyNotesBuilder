package com.example.mynotesbuilder

class Note{
    var title: String? = null
    var description: String? = null

    class NoteBuilder {
        private val note = Note()

        fun setTitle(title: String) = apply {
            note.title = title
        }

        fun setDescription(description: String) = apply {
            note.description = description
        }

        fun build() = note
    }
}

