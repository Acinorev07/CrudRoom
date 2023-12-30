package com.example.crudroom.feature_users.data.local_source.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crudroom.feature_users.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // Aqui crearemos la implementacion para los accesos al conjunto de datos que se ha creado en la data class User y que tiene la anotacion @Entity
    //La primera consulta mostrara todos los datos que hemos creado
    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>//Obtenemos los usuarios con base en el modelo User

    //Aqui llamaremos a un usuario por su id
    @Query("SELECT * FROM User WHERE id =:id")
    suspend fun getUserById(id: Int): User?

    //Con la siguiente orden creamos un usuario o editamos uno ya existente
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:User)

    //Para eliminar un usuario
    @Delete
    suspend fun deleteUser(user:User)
}