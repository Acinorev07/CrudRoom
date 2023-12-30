package com.example.crudroom.feature_users.presentation.home.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

import com.example.crudroom.feature_users.domain.model.User


@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    onEditUser:()->Unit,
    onDeleteUser:()->Unit
    ){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp ),
        elevation = cardElevation(3.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ){
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(30.dp)
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Column(modifier = Modifier.width(150.dp),verticalArrangement = Arrangement.Center) {
                Text(
                    text = "${user.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${user.lastName}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray)) {
                            append(user.age.toString())
                        }

                    },
                    modifier = Modifier.width(150.dp)
                    )

            }
               // Row (modifier = Modifier.width(50.dp)) {
                    IconButton(onClick = onEditUser,modifier = Modifier.width(30.dp)) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = null,
                            tint = Color.Green

                        )
                    }
                    IconButton(onClick = onDeleteUser,modifier = Modifier.width(30.dp)) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Color.Red

                        )
                    }
                //}
            }
        }
    }
