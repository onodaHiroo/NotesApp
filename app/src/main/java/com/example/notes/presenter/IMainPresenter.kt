package com.example.notes.presenter

import com.example.notes.models.INoteList
import com.example.notes.models.Note

interface IMainPresenter {
    fun loadNotes()
    fun addNote(note: Note)
    fun deleteNote(note: Note)
    fun editNote(note: Note)
    fun getNoteById(id: Int)
    fun simpleTest():INoteList
}