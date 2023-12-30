package com.example.crudroom.feature_users.presentation.home

import com.example.crudroom.feature_users.domain.model.User

data class HomeState (
    val users: List<User> = emptyList()
)