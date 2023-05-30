package com.example.notesapp.data

import androidx.room.*
import com.example.notesapp.data.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(note: Note)
    @Insert
    suspend fun add(note: Note)
    @Update
    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("SELECT * FROM notes ORDER BY timestamp")
    fun getAllNotes(): Flow<List<Note>>
    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun getNoteById(noteId: Int): Flow<Note?>
    @Query("SELECT * FROM notes WHERE folderId = :folderId")
    fun getNotesByFolderId(folderId: Int): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :searchTitle || '%'")
    fun getNotesByTitle(searchTitle: String): Flow<List<Note>>
    @Query("SELECT COUNT(*) FROM notes")
    fun getNoteCount(): Flow<Int>
}
