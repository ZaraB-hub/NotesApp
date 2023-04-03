package com.example.notesapp

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class Note(val id:Int=Random().nextInt(Int.MAX_VALUE),var title:String?,var body:String?,var timestamp: Date) :Parcelable
{
    val formattedTimestamp: String
        get() = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(timestamp)
}

var notesList = mutableListOf(
    Note(title = "Bela", body = "My name is Bela", timestamp = Date()),
    Note(title = "Zara", body = "My name is Zara", timestamp = Date()),
    Note(title = "Zlatan", body = "My name is Zlatan", timestamp=Date())
)

