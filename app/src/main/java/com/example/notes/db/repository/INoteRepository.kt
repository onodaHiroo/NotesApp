package com.example.notes.db.repository

import androidx.lifecycle.LiveData
import com.example.notes.models.Note

interface INoteRepository {
    val allNotes: LiveData<List<Note>>
    suspend fun insertNote(note: Note, onSuccess:() -> Unit)
    suspend fun deleteNote(note: Note, onSuccess:() -> Unit)
    suspend fun updateNote(note: Note, onSuccess: () -> Unit)
}