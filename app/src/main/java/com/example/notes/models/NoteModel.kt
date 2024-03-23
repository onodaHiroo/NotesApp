package com.example.notes.models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
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
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteNote(id: Int) {
        notes.removeIf { it.id == id }
    }

    override fun simpleTest():NoteModel {
        notes.add(Note(1, "test1", "test1", Date(), Date()))
        notes.add(Note(2, "test2", "test2", Date(), Date()))
        notes.add(Note(3, "test3", "test3", Date(), Date()))
        notes.add(Note(4, "test4", "test4", Date(), Date()))
        return this
    }


}