package com.example.notesapp.data

import android.icu.text.SimpleDateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes",
    foreignKeys = [ForeignKey(entity = Folder::class,
        parentColumns = ["id"],
        childColumns = ["folderId"],
        onDelete = ForeignKey.CASCADE)])
class Note(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var body: String,
    @ColumnInfo(name = "timestamp") var timestamp: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()),
    @ColumnInfo(name = "folderId") var folderId: Int = Folder.NOTES_FOLDER_ID
)