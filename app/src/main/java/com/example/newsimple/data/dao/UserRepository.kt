package com.example.newsimple.data.dao

import androidx.lifecycle.LiveData
import com.example.newsimple.entities.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }
    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }
    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }
}