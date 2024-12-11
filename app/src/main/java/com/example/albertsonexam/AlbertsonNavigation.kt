package com.example.albertsonexam

import androidx.navigation.NavHostController
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
import com.example.albertsonexam.AlbertsonScreens.USERS_SCREEN
import com.example.albertsonexam.AlbertsonScreens.USER_DETAILS_SCREEN

private object AlbertsonScreens {
    const val USERS_SCREEN = "users"
    const val USER_DETAILS_SCREEN = "userDetails"
}

object AlbertsonArgs {
    const val FIRST_NAME_ARG = "firstname"
    const val LAST_NAME_ARG = "lastname"
    const val STREET_NUMBER_ARG = "streetnumber"
    const val STREET_NAME_ARG = "streetname"
    const val CITY_ARG = "city"
    const val STATE_ARG = "state"
    const val COUNTRY_ARG = "country"
    const val POSTCODE_ARG = "postcode"
    const val LATITUDE_ARG = "latitude"
    const val LONGITUDE_ARG = "longitude"
    const val IMAGE_URL_ARG = "imageurl"
    const val OFFSET_ARG = "offset"
    const val DESCRIPTION_ARG = "description"
}

object AlbertsonDestinations {
    const val USERS_ROUTE = USERS_SCREEN
    const val USER_DETAILS_ROUTE = "$USER_DETAILS_SCREEN?$FIRST_NAME_ARG={$FIRST_NAME_ARG}&$LAST_NAME_ARG={$LAST_NAME_ARG}&$STREET_NUMBER_ARG={$STREET_NUMBER_ARG}&$STREET_NAME_ARG={$STREET_NAME_ARG}&$CITY_ARG={$CITY_ARG}&$STATE_ARG={$STATE_ARG}&$COUNTRY_ARG={$COUNTRY_ARG}&$POSTCODE_ARG={$POSTCODE_ARG}&$LATITUDE_ARG={$LATITUDE_ARG}&$LONGITUDE_ARG={$LONGITUDE_ARG}&$IMAGE_URL_ARG={$IMAGE_URL_ARG}&$OFFSET_ARG={$OFFSET_ARG}&$DESCRIPTION_ARG={$DESCRIPTION_ARG}"
}

class AlbertsonNavigationActions(private val navController: NavHostController) {

    fun navigateToUsers() {
        navController.navigate(USERS_SCREEN)
    }

    fun navigateToUserDetails(firstName: String, lastName: String, streetNumber: String, streetName: String, city: String, state: String, country: String, postcode: String, latitude: String, longitude: String, imageUrl: String, offset: String, description: String) {
        navController.navigate(
            "$USER_DETAILS_SCREEN?$FIRST_NAME_ARG=$firstName&$LAST_NAME_ARG=$lastName&$STREET_NUMBER_ARG=$streetNumber&$STREET_NAME_ARG=$streetName&$CITY_ARG=$city&$STATE_ARG=$state&$COUNTRY_ARG=$country&$POSTCODE_ARG=$postcode&$LATITUDE_ARG=$latitude&$LONGITUDE_ARG=$longitude&$IMAGE_URL_ARG=$imageUrl&$OFFSET_ARG=$offset&$DESCRIPTION_ARG=$description"
        )
    }

}