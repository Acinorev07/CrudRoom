package com.example.crudroom.feature_users.presentation.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crudroom.R
import com.example.crudroom.feature_users.presentation.edit.components.UserInputText
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen (
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel(),

){

    val nameState = viewModel.userName.value
    val lastNameState = viewModel.userLastName.value
    val ageState = viewModel.userAge.value

    LaunchedEffect(key1 = true){
        viewModel.eventeFlow.collectLatest{event->
            when (event){
                is EditViewModel.UiEvent.SaveUser ->{
                    navController.navigateUp()
                }
            }

        }
    }

    Scaffold(
        topBar = {
            EditTopBar(
                topAppBarText = stringResource(id = R.string.add_user)
            )
        },
        content = {innerPadding->
            EditContent(
                name = nameState.text,
                lastName = lastNameState.text,
                age = ageState.text,
                onEvent ={it->
                    viewModel.onEvent(it)
                },
                modifier = Modifier.padding(innerPadding)

            )
        },
        bottomBar ={
            EditBottonBar(
                onInsertUser = {
                    viewModel.onEvent(EditEvent.InsertUser)
                }
            )
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTopBar(
    topAppBarText: String
){
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.surface)
    )
}

@Composable
fun EditContent(
    modifier: Modifier = Modifier,
    name:String,
    lastName: String,
    age: String,
    onEvent: (EditEvent)->Unit
){
    Column (
        modifier = Modifier.fillMaxWidth()
            ){
        Spacer(modifier = Modifier.height(44.dp))
        UserInputText(
            text = name,
            hint = stringResource(id=R.string.name),
            onTextChange={onEvent(EditEvent.EnteredName(it))}
        )
        UserInputText(
            text =  lastName,
            hint = stringResource(id=R.string.last_name),
            onTextChange={onEvent(EditEvent.EnteredLastName(it))}
        )
        UserInputText(
            text = age,
            hint = stringResource(id=R.string.age),
            onTextChange={onEvent(EditEvent.EnteredAge(it))}
        )

    }
}

@Composable
fun EditBottonBar(
    modifier: Modifier = Modifier,
    onInsertUser:()->Unit
){
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 14.dp),
        onClick = {
            onInsertUser()
        }
    ){
        Text(
            text = stringResource(id = R.string.add_user)
        )
    }
}