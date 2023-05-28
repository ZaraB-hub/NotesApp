package com.example.notesapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.AppDatabase
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteDao: NoteDao
    val allNotes: Flow<List<Note>>

    init {
        val database = AppDatabase.getInstance(application)
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun upsert(note: Note) = viewModelScope.launch {
        noteDao.upsert(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteDao.delete(note)
    }

    fun getNoteById(noteId: Int): Flow<Note?> {
        return noteDao.getNoteById(noteId)
    }

    fun getNotesByTitle(searchTitle: String): Flow<List<Note>>{
        return noteDao.getNotesByTitle(searchTitle)
    }
}

