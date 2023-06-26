package com.example.mynotesbuilder

data class NoteState(
    val notes: List<Note> = emptyList(),
    val state: String = ""
)
