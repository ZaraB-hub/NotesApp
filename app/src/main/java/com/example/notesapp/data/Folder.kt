package com.example.notesapp.data

import android.icu.text.SimpleDateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "folders")
class Folder(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
){
    companion object {
        const val NOTES_FOLDER_ID = 1
    }
}