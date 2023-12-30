package com.example.crudroom.feature_users.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name= "last_name")
    val lastName : String,
    @ColumnInfo(name= "age")
    val age : Int

)
