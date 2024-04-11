package com.example.notes.models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*
import java.util.Collections.emptyList

class NoteModel : INoteList {

    private val notes = mutableListOf<Note>()
    private val notes2 = emptyList<Note>()
    //private val notes = emptyList<Note>()


    override fun getAllNotes(): List<Note> {
        return notes.toList<Note>()
    }

    override fun getNoteById(id: Int): Note? {
        return notes.find { it.id == id }
    }

    override fun addNote(note: Note): Note {
        notes.add(note)
        return note
    }

    override fun editNote(note: Note) {
        if (note.id != -1) {
            note.createDate = notes[note.id].createDate
            notes[note.id] = note
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteNote(id: Int) {
        notes.removeAt(id)
        Log.d("Check IDs of list", "${notes}")
    }

    ////test
    override fun simpleTest():NoteModel {
        notes.add(Note(getLastId(), "test${getLastId()}", "test${getLastId()}", Date().toString(), Date().toString()))
        notes.add(Note(getLastId(), "test${getLastId()}", "test${getLastId()}", Date().toString(), Date().toString()))
        notes.add(Note(getLastId(), "test${getLastId()}", "test${getLastId()}", Date().toString(), Date().toString()))
        return this
    }

    override fun getLastId(): Int {
        var lastIndexOfList = 0
        if (notes.lastIndex == -1) { lastIndexOfList = 0 }
        else lastIndexOfList = notes.lastIndex + 1

        return lastIndexOfList
    }
    ////test

    fun insert(note: Note, onSuccess:() -> Unit){

    }

}