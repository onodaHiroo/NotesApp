package com.example.notes.presenter

import com.example.notes.models.Note

interface IMainPresenter {
    fun loadNotes()

    fun detach()

    //repository presenter methods
    fun insertNote(note: Note, onSuccess:() -> Unit)
    fun deleteNote(note: Note, onSuccess:() -> Unit)
    fun editNote(note: Note, onSuccess:() -> Unit)
}