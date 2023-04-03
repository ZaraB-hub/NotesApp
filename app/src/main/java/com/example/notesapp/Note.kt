package com.example.notesapp

import java.text.SimpleDateFormat
import java.util.*


data class Note(val id:Int=Random().nextInt(Int.MAX_VALUE),var title:String?,var body:String?,var timestamp: Date)
{
    val formattedTimestamp: String
        get() = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(timestamp)
}

val notesList = listOf(
    Note(title = "Bela", body = "null", timestamp = Date()),
    Note(title = "Zara", body = "My name is Zara", timestamp = Date()),
    Note(title = "Zlatan", body = "dlvkd", timestamp = Date()),
    Note(title = "Selveta", body = "mdklvd", timestamp = Date())
)
