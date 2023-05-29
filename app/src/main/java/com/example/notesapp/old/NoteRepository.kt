package com.example.notesapp.old

class NoteRepository(private val notesList: MutableList<Note>) {

    fun addNewNote(): Note {
        val newNote = Note()
        notesList.add(newNote)
        return newNote
    }

    fun deleteNote(note: Note) {
        notesList.remove(note)
    }

}