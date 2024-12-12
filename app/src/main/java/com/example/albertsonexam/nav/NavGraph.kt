package com.example.albertsonexam.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.albertsonexam.users.UserDetailsScreen
import com.example.albertsonexam.users.UsersScreen
import com.example.albertsonexam.users.UsersViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.USERS_SCREEN,
    sharedUsersViewModel: UsersViewModel = hiltViewModel(),
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    }
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            Screens.USERS_SCREEN
        ) {
            UsersScreen(viewModel = sharedUsersViewModel) {
                navActions.navigateToUserDetails()
            }
        }

        composable(
            Screens.USER_DETAILS_SCREEN
        ) {
            UserDetailsScreen(
                viewModel = sharedUsersViewModel
            )
        }

    }
}