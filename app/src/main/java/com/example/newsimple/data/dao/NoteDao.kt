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

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>
}