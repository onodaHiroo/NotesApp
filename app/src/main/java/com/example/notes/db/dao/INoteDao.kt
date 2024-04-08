package com.example.notes.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.models.Note

@Dao
interface INoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * from note_table")
    fun getAllNotes(): LiveData<List<Note>>
}