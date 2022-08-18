package com.example.newsimple.data.dao

import androidx.lifecycle.LiveData
import com.example.newsimple.entities.Note

class NoteRepository(private val noteDao: NoteDao) {
    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
}