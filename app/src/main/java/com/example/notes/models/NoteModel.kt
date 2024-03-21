package com.example.notes.models

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class NoteModel : INoteList {

    private val notes = mutableListOf<Note>()
    private val notes2 = emptyList<Note>()
    //private val notes = emptyList<Note>()


    override fun getAllNotes(): List<Note> {
        return notes.toList<Note>()
    }

    override fun getNote(id: Int): Note? {
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


}