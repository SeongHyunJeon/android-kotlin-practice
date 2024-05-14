package com.gettingold.firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gettingold.firebase.ui.screens.addscreen.UserAddScreen
import com.gettingold.firebase.ui.screens.editscreen.UserEditScreen
import com.gettingold.firebase.ui.screens.listscreen.UserListScreen

@Composable
fun UserRegistrationNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        startDestination = UserListDestination.route,
        navController = navController,
        modifier = modifier
    ) {
        composable(route = UserListDestination.route) {
            UserListScreen(
                navigateToEditScreen = { navController.navigate("${UserEditDestination.route}/${it}") },
                navigateToAddScreen = { navController.navigate("${UserAddDestination.route}") },
                viewModel = hiltViewModel()
            )
        }
        composable(
            route = UserEditDestination.routeWithArgs,
            arguments = listOf(navArgument(UserEditDestination.userIdArg) {
                type = NavType.StringType
            })
        ) {
            UserEditScreen(
                navigateBack = { navController.popBackStack() },
                viewModel = hiltViewModel()
            )
        }
        composable(route = UserAddDestination.route) {
            UserAddScreen(
                navigateBack = { navController.popBackStack() },
                viewModel = hiltViewModel()
            )
        }
    }
}