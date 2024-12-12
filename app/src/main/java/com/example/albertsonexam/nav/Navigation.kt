package com.example.albertsonexam.nav

import androidx.navigation.NavHostController
import com.example.albertsonexam.nav.Screens.USERS_SCREEN
import com.example.albertsonexam.nav.Screens.USER_DETAILS_SCREEN

object Screens {
    const val USERS_SCREEN = "users"
    const val USER_DETAILS_SCREEN = "userDetails"
}

class NavigationActions(private val navController: NavHostController) {

    fun navigateToUsers() {
        navController.navigate(USERS_SCREEN)
    }

    fun navigateToUserDetails() {
        navController.navigate(USER_DETAILS_SCREEN)
    }

}