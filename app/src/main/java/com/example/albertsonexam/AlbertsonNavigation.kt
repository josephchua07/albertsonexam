package com.example.albertsonexam

import androidx.navigation.NavHostController
import com.example.albertsonexam.AlbertsonArgs.FIRST_NAME_ARG
import com.example.albertsonexam.AlbertsonArgs.LAST_NAME_ARG
import com.example.albertsonexam.AlbertsonScreens.USERS_SCREEN
import com.example.albertsonexam.AlbertsonScreens.USER_DETAILS_SCREEN

private object AlbertsonScreens {
    const val USERS_SCREEN = "users"
    const val USER_DETAILS_SCREEN = "userDetails"
}

object AlbertsonArgs {
    const val FIRST_NAME_ARG = "firstname"
    const val LAST_NAME_ARG = "lastname"
}

object AlbertsonDestinations {
    const val USERS_ROUTE = USERS_SCREEN
    const val USER_DETAILS_ROUTE = "$USER_DETAILS_SCREEN?$FIRST_NAME_ARG={$FIRST_NAME_ARG}&$LAST_NAME_ARG={$LAST_NAME_ARG}"
}

class AlbertsonNavigationActions(private val navController: NavHostController) {

    fun navigateToUsers() {
        navController.navigate(USERS_SCREEN)
    }

    fun navigateToUserDetails(firstName: String, lastName: String) {
        navController.navigate(
            "$USER_DETAILS_SCREEN?$FIRST_NAME_ARG=$firstName&$LAST_NAME_ARG=$lastName"
        )
    }

}