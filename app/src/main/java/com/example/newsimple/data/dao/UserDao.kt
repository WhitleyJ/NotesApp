package com.example.newsimple.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsimple.entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM USER_TABLE ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>
}