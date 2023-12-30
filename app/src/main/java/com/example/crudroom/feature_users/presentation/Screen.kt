package com.example.crudroom.feature_users.presentation

sealed class Screen(val route:String) {
    object Home:Screen("home")
    object Edit:Screen("edit?userId={userId}"){
        //Aqui creamos una funcion para facilitar el correcto funcionamiento de pasar una ruta de pantalla a pantalla
        fun passId(userId:Int?):String{
            return "edit?userId=$userId"
        }
    }
}