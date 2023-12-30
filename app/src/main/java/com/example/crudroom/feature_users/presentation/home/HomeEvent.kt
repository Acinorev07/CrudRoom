package com.example.crudroom.feature_users.presentation.home

import com.example.crudroom.feature_users.domain.model.User

sealed class HomeEvent {
    data class DeleteUser(val user: User):HomeEvent()
}