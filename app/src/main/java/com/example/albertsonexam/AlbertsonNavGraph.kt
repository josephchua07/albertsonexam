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
import com.example.albertsonexam.AlbertsonArgs.CITY_ARG
import com.example.albertsonexam.AlbertsonArgs.COUNTRY_ARG
import com.example.albertsonexam.AlbertsonArgs.DESCRIPTION_ARG
import com.example.albertsonexam.AlbertsonArgs.FIRST_NAME_ARG
import com.example.albertsonexam.AlbertsonArgs.IMAGE_URL_ARG
import com.example.albertsonexam.AlbertsonArgs.LAST_NAME_ARG
import com.example.albertsonexam.AlbertsonArgs.LATITUDE_ARG
import com.example.albertsonexam.AlbertsonArgs.LONGITUDE_ARG
import com.example.albertsonexam.AlbertsonArgs.OFFSET_ARG
import com.example.albertsonexam.AlbertsonArgs.POSTCODE_ARG
import com.example.albertsonexam.AlbertsonArgs.STATE_ARG
import com.example.albertsonexam.AlbertsonArgs.STREET_NAME_ARG
import com.example.albertsonexam.AlbertsonArgs.STREET_NUMBER_ARG
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
                navActions.navigateToUserDetails(user.name.first, user.name.last, user.location.street.number.toString(), user.location.street.name, user.location.city, user.location.state, user.location.country, user.location.postcode, user.location.coordinates.latitude, user.location.coordinates.longitude, user.picture.large, user.location.timezone.offset, user.location.timezone.description)
            }
        }

        composable(
            AlbertsonDestinations.USER_DETAILS_ROUTE,
            arguments = listOf(
                navArgument(FIRST_NAME_ARG) { type = NavType.StringType },
                navArgument(LAST_NAME_ARG) { type = NavType.StringType },
                navArgument(STREET_NUMBER_ARG) { type = NavType.StringType },
                navArgument(STREET_NAME_ARG) { type = NavType.StringType },
                navArgument(CITY_ARG) { type = NavType.StringType },
                navArgument(STATE_ARG) { type = NavType.StringType },
                navArgument(COUNTRY_ARG) { type = NavType.StringType },
                navArgument(POSTCODE_ARG) { type = NavType.StringType },
                navArgument(LATITUDE_ARG) { type = NavType.StringType },
                navArgument(LONGITUDE_ARG) { type = NavType.StringType },
                navArgument(IMAGE_URL_ARG) { type = NavType.StringType },
                navArgument(OFFSET_ARG) { type = NavType.StringType },
                navArgument(DESCRIPTION_ARG) { type = NavType.StringType },
            )
        ) {
            val firstName = it.arguments?.getString(FIRST_NAME_ARG) ?: ""
            val lastName = it.arguments?.getString(LAST_NAME_ARG) ?: ""
            val streetNumber = it.arguments?.getString(STREET_NUMBER_ARG) ?: ""
            val streetName = it.arguments?.getString(STREET_NAME_ARG) ?: ""
            val city = it.arguments?.getString(CITY_ARG) ?: ""
            val state = it.arguments?.getString(STATE_ARG) ?: ""
            val country = it.arguments?.getString(COUNTRY_ARG) ?: ""
            val postcode = it.arguments?.getString(POSTCODE_ARG) ?: ""
            val latitude = it.arguments?.getString(LATITUDE_ARG) ?: ""
            val longitude = it.arguments?.getString(LONGITUDE_ARG) ?: ""
            val imageUrl = it.arguments?.getString(IMAGE_URL_ARG) ?: ""
            val offset = it.arguments?.getString(OFFSET_ARG) ?: ""
            val description = it.arguments?.getString(DESCRIPTION_ARG) ?: ""

            UserDetailsScreen(firstName, lastName, streetNumber, streetName, city, state, country, postcode, latitude, longitude, imageUrl, offset, description)
        }

    }
}