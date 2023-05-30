package com.example.notesapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(folder: Folder)

    @Delete
    suspend fun delete(folder: Folder)

    @Query("SELECT * FROM folders")
    fun getAll(): Flow<List<Folder>>

    @Query("SELECT * FROM folders WHERE id = :folderId")
    fun getFolderById(folderId: Int): Flow<Folder?>

}
