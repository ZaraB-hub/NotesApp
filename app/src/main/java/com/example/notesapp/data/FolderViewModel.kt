package com.example.notesapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FolderViewModel(application: Application) : AndroidViewModel(application) {
    private val folderDao: FolderDao
    val allFolder: Flow<List<Folder>>

    init {
        val database = AppDatabase.getInstance(application)
        folderDao = database.folderDao()
        allFolder = folderDao.getAll()
    }

    fun upsert(folder: Folder) = viewModelScope.launch {
        folderDao.upsert(folder)
    }
    fun delete(folder: Folder) = viewModelScope.launch {
        folderDao.delete(folder)
    }

    fun getFolderById(folderId: Int): Flow<Folder?> {
        return folderDao.getFolderById(folderId)
    }
}
