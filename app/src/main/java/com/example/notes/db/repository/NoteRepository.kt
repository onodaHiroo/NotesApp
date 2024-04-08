package com.example.notes.db.repository

import androidx.lifecycle.LiveData
import com.example.notes.db.dao.INoteDao
import com.example.notes.models.Note

class NoteRepository(private val noteDao: INoteDao): INoteRepository {



    override val allNotes: LiveData<List<Note>>
        get() = noteDao.getAllNotes()

    override suspend fun insertNote(note: Note, onSuccess: () -> Unit) {
        noteDao.insert(note)
        onSuccess()
    }

    override suspend fun deleteNote(note: Note, onSuccess: () -> Unit) {
        noteDao.delete(note)
        onSuccess()
    }

    override suspend fun updateNote(note: Note, onSuccess: () -> Unit) {

    }
}