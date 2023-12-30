package com.example.crudroom.feature_users.presentation.home

import android.provider.CalendarContract
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crudroom.R
import com.example.crudroom.feature_users.domain.model.User
import com.example.crudroom.feature_users.presentation.Screen
import com.example.crudroom.feature_users.presentation.home.components.UserItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: homeViewModel = hiltViewModel()
    ){

    val state = viewModel.state.value
    Scaffold(
        topBar = {
            HomeTopBar()
        },
        floatingActionButton = {
            HomeFab(
                onFabClicked ={
                    navController.navigate(Screen.Edit.route)
                }
            )
        },
        content = {innerPadding ->

            HomeContent(
                modifier = Modifier.padding(innerPadding),
                onDeleteUser = {viewModel.onEvent(HomeEvent.DeleteUser(it))},
                onEditUser = {
                    // En este metodo cada vez que se oprima el boton editar de algun usuario , se le pasara el id de ese usuario al metodo
                    navController.navigate(
                        route = Screen.Edit.passId(it)
                    )
                },
            users = state.users

            )

        }
    )

}

// Aqui definimos las 3 funciones del Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(id= R.string.users),
                textAlign= TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)

            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.surface)
    )
}

@Composable
fun HomeFab(
    modifier:Modifier = Modifier,
    onFabClicked: () -> Unit = { }
){
    //Para crear un nuevo usuario, al oprimir sobre este boton nos llevara a la ventana donde se hace el registro
    FloatingActionButton(
        onClick = onFabClicked,
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        containerColor = MaterialTheme.colorScheme.primary
    ){
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = stringResource(id = R.string.add_user)
        )
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onDeleteUser: (user: User)->Unit,
    onEditUser: (id: Int?)-> Unit,
    users: List<User> = emptyList()
){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier =  modifier
    ){
        LazyColumn{
            items(users){user->
                UserItem(user = user,
                    onEditUser = { onEditUser(user.id) },
                    onDeleteUser = {onDeleteUser(user)}
                    )
            }
        }
    }
}

