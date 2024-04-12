package com.example.notes.views

import com.example.notes.models.Note

interface IMainActivity {
    fun showNotes(notes: List<Note>)
    fun initialFAB()
    fun deleteNotes(note: Note)
    fun showEditTextDialog(note: Note, title: String? = null, text: String? = null, add: Boolean)
    fun editNotes(note: Note)

    //delete after all
    //fun deleteNotes(noteId: Int)
    //fun showEditTextDialog(id: Int? = null, title: String? = null, text: String? = null)

}