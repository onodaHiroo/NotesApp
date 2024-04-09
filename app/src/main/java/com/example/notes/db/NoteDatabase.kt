package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.db.dao.INoteDao
import com.example.notes.models.Note
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase(){

    abstract fun getNoteDao():INoteDao

    companion object{
        private var database: NoteDatabase ?= null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): NoteDatabase? {
                synchronized(this) {
                    if (database == null) {
                        database = Room.databaseBuilder(context.applicationContext,  NoteDatabase::class.java, "db").build()
                    }
                    return database
                }
        }
    }
}


//МЕНЯТЬ ВЕРСИЮ КАЖДЫЙ РАЗ КОГДА ИЗМЕНЯЕШЬ ЭТОТ КЛАСС ИЛИ ДАО