package com.example.crudroom.feature_users.presentation.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudroom.feature_users.domain.model.User
import com.example.crudroom.feature_users.domain.use_cases.getUser
import com.example.crudroom.feature_users.domain.use_cases.insertUser

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getUser: getUser,
    private val insertUser: insertUser,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _userName = mutableStateOf(TextFieldState())
    val userName: State<TextFieldState> = _userName

    private val _userLastName = mutableStateOf(TextFieldState())
    val userLastName: State<TextFieldState> = _userLastName

    private val _userAge = mutableStateOf(TextFieldState())
    val userAge: State<TextFieldState> = _userAge

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventeFlow = _eventFlow.asSharedFlow()


    private var currentUserId: Int? = null

    //Vamos a crear la pantalla editScreen,poniendo o recuperando el id
    init{
        //De la siguiente manera vamos a recuperar el valor del id
        savedStateHandle.get<Int>("userId")?.let{userId->

            //Como en el navegation colocamos un valor por defecto del user id en -1
            //a continuacion hacemos la comprobacion de que sea diferente a -1
            if(userId !== -1){
                viewModelScope.launch{
                    getUser(userId)?.also{user->

                        currentUserId = user.id
                        _userName.value = userName.value.copy(
                            text = user.name
                        )
                        _userLastName.value = userLastName.value.copy(
                            text = user.lastName
                        )
                        _userAge.value = userAge.value.copy(
                            text = user.age.toString()
                        )
                    }
                }
            }

        }
    }

    //Aqui manejaremos los eventos, inserta los valores en los cuadros de texto cuando se desee editar

    fun onEvent(event:EditEvent){
        when (event){
            is EditEvent.EnteredName->{
                _userName.value = userName.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredLastName->{
                _userLastName.value = userLastName.value.copy(
                    text = event.value
                )
            }
            is EditEvent.EnteredAge->{
                _userAge.value = userAge.value.copy(
                    text = event.value
                )
            }
            EditEvent.InsertUser->{
                viewModelScope.launch{
                    insertUser(
                        User(
                            name = userName.value.text,
                            lastName = userLastName.value.text,
                            age = userAge.value.text.toInt(),
                            id = currentUserId
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveUser)
                }
            }
        }
    }

    sealed class UiEvent{
        object SaveUser:UiEvent()
    }

}