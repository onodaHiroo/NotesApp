package com.example.notes.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo
    var title: String,
    @ColumnInfo
    var text: String,
    @ColumnInfo
    var changeDate: Date?,
    @ColumnInfo
    var createDate: Date?
    )