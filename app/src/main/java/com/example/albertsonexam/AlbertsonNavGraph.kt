package com.example.albertsonexam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.albertsonexam.AlbertsonArgs.FIRST_NAME_ARG
import com.example.albertsonexam.AlbertsonArgs.LAST_NAME_ARG
import com.example.albertsonexam.userdetails.UserDetailsScreen
import com.example.albertsonexam.users.UsersScreen

@Composable
fun AlbertsonNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AlbertsonDestinations.USERS_ROUTE,
    navActions: AlbertsonNavigationActions = remember(navController) {
        AlbertsonNavigationActions(navController)
    }
) {

//    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = currentNavBackStackEntry?.destination?.route ?: startDestination

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            AlbertsonDestinations.USERS_ROUTE
        ) {
            UsersScreen { user ->
                navActions.navigateToUserDetails(user.name.first, user.name.last)
            }
        }

        composable(
            AlbertsonDestinations.USER_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(FIRST_NAME_ARG) { type = NavType.StringType },
                navArgument(LAST_NAME_ARG) { type = NavType.StringType }
            )
        ) {
            val firstName = it.arguments?.getString(FIRST_NAME_ARG) ?: ""
            val lastName = it.arguments?.getString(LAST_NAME_ARG) ?: ""
            UserDetailsScreen(firstName, lastName)
        }

    }
}