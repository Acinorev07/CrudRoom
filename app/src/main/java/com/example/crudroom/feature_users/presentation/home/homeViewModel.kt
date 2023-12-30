package com.example.crudroom.feature_users.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudroom.feature_users.domain.use_cases.deleteUser
import com.example.crudroom.feature_users.domain.use_cases.getUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class homeViewModel @Inject constructor(
    private val deleteUser: deleteUser,
    private val getUsers: getUsers
): ViewModel() {

    //Aqui haremos referencia al homeState para manejar los estados
    private val _state = mutableStateOf (HomeState())
    val state: State<HomeState> = _state

    init{
        getUsers().onEach{users->
            _state.value = state.value.copy(
                users= users
            )
        }.launchIn(viewModelScope)
    }

    //Aqui creamos una funcion para ir atrapando los eventos (que es uno en este caso)
    fun onEvent (event: HomeEvent){
        when (event){
            is HomeEvent.DeleteUser->{
                viewModelScope.launch{
                    deleteUser(event.user)
                }
            }
        }
    }
}