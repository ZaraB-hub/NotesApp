package com.example.notesapp.data

import android.icu.text.SimpleDateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String? = "Empty Note",
    @ColumnInfo(name = "content") var body: String? = null,
    @ColumnInfo(name = "timestamp") var timestamp: Long=System.currentTimeMillis()


)