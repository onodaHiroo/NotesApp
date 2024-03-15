package com.example.notes.models

interface INoteList {
    fun TestAddNotes(): MutableList<Note>
    fun ShowNoteList()

    fun getAllNotes() : List<Note>
    fun getNote(id: Int) : Note?
    fun addNote(note: Note) : Note
    fun editNote(note: Note)
    fun deleteNote(id: Int)
}