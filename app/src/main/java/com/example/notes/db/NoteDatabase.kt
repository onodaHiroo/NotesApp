package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import com.example.notes.db.dao.INoteDao
import com.example.notes.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase(){
    abstract fun getNoteDao():INoteDao

    companion object{

        private var database: NoteDatabase ?= null

        @Synchronized
        fun getInstance(context: Context):NoteDatabase{
            return if (database == null){
                database = Room.databaseBuilder(context, NoteDatabase::class.java, "db").build()
                database as NoteDatabase
            }else{
                database as NoteDatabase
            }
        }
    }
}


//МЕНЯТЬ ВЕРСИЮ КАЖДЫЙ РАЗ КОГДА ИЗМЕНЯЕШЬ ЭТОТ КЛАСС ИЛИ ДАО