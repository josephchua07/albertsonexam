package com.example.albertsonexam.userdetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserDetailsScreen(
    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier
) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->
        Column(modifier.padding(paddingValues)) {
            Text(text = "$firstName $lastName")
        }
    }

}