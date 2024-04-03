package com.example.notes.views

import com.example.notes.models.Note

interface IMainActivity {
    fun showNotes(notes: List<Note>)
    fun initialFAB()
    fun deleteNotes(noteId: Int)
    fun editNotes(note: Note)
    fun showEditTextDialog(id: Int? = null, title: String? = null, text: String? = null)
}