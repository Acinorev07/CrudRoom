package com.example.crudroom.feature_users.domain.use_cases

import com.example.crudroom.feature_users.domain.model.User
import com.example.crudroom.feature_users.domain.repository.UserRepository
import javax.inject.Inject

class insertUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User){
        repository.insertUser(user)
    }
}