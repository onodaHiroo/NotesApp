package com.example.notes.models

import java.util.*

data class Note (val id: Int, var title: String, var text: String, var createDate: Date, var changeDate: Date)