package com.example.notesapp.data

import android.icu.text.SimpleDateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var body: String,
    @ColumnInfo(name = "timestamp") var timestamp: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

)