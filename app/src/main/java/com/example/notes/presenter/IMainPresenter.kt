package com.example.notes.presenter

import com.example.notes.models.INoteList
import com.example.notes.models.Note

interface IMainPresenter {
    fun loadNotes()

//    fun addNote(note: Note)
//    fun deleteNote(noteId: Int)
//    fun editNote(note: Note)
//    fun getNoteById(id: Int)
    fun simpleTest():INoteList
    fun detach()
    fun getLastId():Int

    //repository presenter methods
    fun insertNote(note: Note, onSuccess:() -> Unit)
    fun deleteNote(note: Note, onSuccess:() -> Unit)
    fun editNote(note: Note, onSuccess:() -> Unit)
}