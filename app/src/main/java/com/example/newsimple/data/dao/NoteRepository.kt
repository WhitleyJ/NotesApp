package com.example.newsimple.data.dao

import androidx.lifecycle.LiveData
import com.example.newsimple.entities.Note

class NoteRepository(private val noteDao: NoteDao) {
    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    fun readHighNotes(): LiveData<List<Note>> = noteDao.getHighNotes()
    fun readMiddleNotes(): LiveData<List<Note>> = noteDao.getMiddleNotes()
    fun readLowNotes(): LiveData<List<Note>> = noteDao.getLowNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
    suspend fun deleteAllNotes(){
        noteDao.deleteNotes()
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }
}