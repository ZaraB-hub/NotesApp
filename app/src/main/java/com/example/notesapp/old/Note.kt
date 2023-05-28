package com.example.notesapp.old

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class Note(val id:Int=Random().nextInt(Int.MAX_VALUE),var title:String?="Empty Note",var body:String?=null,var timestamp: Date=Date()) :Parcelable
{
    val formattedTimestamp: String
        get() = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(timestamp)
}

var notesList = mutableListOf(
    Note(title = "Bela", body = "My name is Bela", timestamp = Date(58437593)),
    Note(title = "Zara", body = "My name is Zara", timestamp = Date()),
    Note(title = "Zlatan", body = "My name is Zlatan", timestamp=Date())
)

