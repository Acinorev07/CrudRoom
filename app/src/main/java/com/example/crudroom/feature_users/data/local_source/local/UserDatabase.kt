package com.example.crudroom.feature_users.data.local_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.crudroom.feature_users.data.local_source.local.daos.UserDao
import com.example.crudroom.feature_users.domain.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDao
}