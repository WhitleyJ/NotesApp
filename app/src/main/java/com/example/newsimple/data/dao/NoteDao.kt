package com.example.newsimple.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsimple.entities.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteNotes()


    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE priory = 1")
    fun getHighNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE priory = 2")
    fun getMiddleNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE priory = 3")
    fun getLowNotes(): LiveData<List<Note>>
}