package com.gettingold.firebase

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.gettingold.firebase.ui.navigation.UserRegistrationNavHost

@Composable
fun UserRegistrationApp() {
    val navController: NavHostController = rememberNavController()
    UserRegistrationNavHost(navController = navController)
}