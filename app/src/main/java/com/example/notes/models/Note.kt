package com.example.notes.models

import androidx.room.Entity
import java.util.*

@Entity
data class Note (
    val id: Int,
    var title: String,
    var text: String,
    var changeDate: Date?,
    var createDate: Date?
    )