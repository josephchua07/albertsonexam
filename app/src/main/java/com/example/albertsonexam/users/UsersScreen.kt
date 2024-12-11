package com.example.albertsonexam.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.albertsonexam.data.source.UserResponse
import com.example.albertsonexam.ui.theme.AlbertsonexamTheme

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel(),
    onUserClick: (UserResponse) -> Unit
) {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Greeting(modifier = Modifier.padding(innerPadding))

    }

}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "Hello!",
        modifier = modifier
    )
}

@Preview
@Composable
private fun UserScreenPreview() {
    AlbertsonexamTheme {
        Greeting()
    }
}