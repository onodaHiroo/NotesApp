package com.example.notes.views

import com.example.notes.models.Note

interface IMainActivity {
    fun showNotes(notes: List<Note>)
    fun showEditTextDialog()
}