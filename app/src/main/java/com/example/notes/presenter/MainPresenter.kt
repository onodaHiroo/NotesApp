package com.example.notes.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notes.models.Note
import com.example.notes.models.NoteModel
import com.example.notes.views.MainActivity

class MainPresenter (private val view: MainActivity, private val model: NoteModel) : IMainPresenter {


    override fun loadNotes() {
        view.showNotes(model.getAllNotes())
    }

    override fun addNote(note: Note) {
        model.addNote(note)
        view.showNotes(model.getAllNotes())
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteNote(note: Note) {
        model.deleteNote(note.id)
        view.showNotes(model.getAllNotes())
    }

    override fun editNote(note: Note) {
        model.editNote(note)
        view.showNotes(model.getAllNotes())
    }


}