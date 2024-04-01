package com.example.notes.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notes.models.INoteList
import com.example.notes.models.Note
import com.example.notes.views.IMainActivity

class MainPresenter (private var view: IMainActivity?, private val model: INoteList) : IMainPresenter {

    override fun loadNotes() {
        view?.showNotes(model.getAllNotes())
    }

    override fun addNote(note: Note) {
        model.addNote(note)
        view?.showNotes(model.getAllNotes())
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteNote(note: Note) {
        model.deleteNote(note.id)
        view?.showNotes(model.getAllNotes())
    }

    override fun editNote(note: Note) {
        model.editNote(note)
        view?.showNotes(model.getAllNotes())
    }

    override fun getNoteById(id: Int) {
        val note = model.getNoteById(id)
        if (note != null) {
            //view.displayNoteDetails(note)
        } else {
            //view.showNoteNotFoundError()
        }
    }

    override fun simpleTest():INoteList {
        return model.simpleTest()
    }

    override fun detach() { view = null }

}