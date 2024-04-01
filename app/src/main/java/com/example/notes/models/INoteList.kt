package com.example.notes.models

interface INoteList {

    fun getAllNotes() : List<Note>
    fun getNoteById(id: Int) : Note?
    fun addNote(note: Note) : Note
    fun editNote(note: Note)
    fun deleteNote(id: Int)
    fun simpleTest():NoteModel
    fun getLastId():Int
}