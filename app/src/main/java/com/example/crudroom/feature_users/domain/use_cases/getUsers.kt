package com.example.crudroom.feature_users.domain.use_cases

import com.example.crudroom.feature_users.domain.model.User
import com.example.crudroom.feature_users.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class getUsers @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<List<User>>{
        return repository.getUsers()
    }
}