package com.example.albertsonexam.users

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.albertsonexam.R
import com.example.albertsonexam.data.models.CoordinatesResponse
import com.example.albertsonexam.data.models.LocationResponse
import com.example.albertsonexam.data.models.NameResponse
import com.example.albertsonexam.data.models.PictureResponse
import com.example.albertsonexam.data.models.StreetResponse
import com.example.albertsonexam.data.models.TimezoneResponse
import com.example.albertsonexam.data.models.UserResponse
import com.example.albertsonexam.ui.theme.AlbertsonexamTheme

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel(),
    onUserClick: () -> Unit
) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->

        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        var inputText by remember { mutableStateOf("") }

        BackHandler {
            viewModel.resetUIState()
        }

        Box(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (uiState.value.errorMessage != null) {
                Text(
                    text = uiState.value.errorMessage ?: "Unknown error",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }

            if (uiState.value.users.isEmpty().not()) {
                UsersList(
                    modifier,
                    uiState.value.users,
                ) { user ->
                    viewModel.selectUser(user)
                    onUserClick()
                }
            } else if (uiState.value.showInput && uiState.value.users.isEmpty()) {
                Column(
                    Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = inputText,
                        onValueChange = { newText ->
                            inputText = newText.filter { it.isDigit() }
                        },
                        label = { Text("Enter desired number of results (1 - 5000)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            viewModel.validateInput(inputText.toIntOrNull() ?: 0)
                        },
                        enabled = inputText.isNotBlank()
                    ) {
                        Text("Fetch Users")
                    }
                }
            }
        }
    }
}

@Composable
fun UsersList(
    modifier: Modifier = Modifier,
    users: List<UserResponse>,
    onUserClick: (UserResponse) -> Unit
) {
    LazyColumn(modifier.fillMaxSize()) {
        items(users) { user ->
            UserCard(user, onUserClick)
        }
    }
}

@Composable
fun UserCard(
    user: UserResponse,
    onClick: (UserResponse) -> Unit
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onClick(user) }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.picture.medium)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_android_black_24dp)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = "${user.name.first} ${user.name.last}")
                Text(text = "${user.location.street.number} ${user.location.street.name} ${user.location.city}, ${user.location.state} ${user.location.country} ${user.location.postcode} ${user.location.coordinates.latitude} ${user.location.coordinates.longitude} ${user.location.timezone.offset} ${user.location.timezone.description}")
            }

        }
    }
}

@Preview
@Composable
private fun UserScreenPreview() {
    AlbertsonexamTheme {
        UsersList(
            users = listOf(
                UserResponse(
                    name = NameResponse("Mr.", "John", "Doe"),
                    gender = "Male",
                    email = "j.doe@sample.com",
                    location = LocationResponse(
                        street = StreetResponse(123, "Main St"),
                        city = "Paranaque",
                        state = "Manila",
                        country = "Philippines",
                        postcode = "12345",
                        coordinates = CoordinatesResponse("12.34", "56.78"),
                        timezone = TimezoneResponse("UTC+08:00", "Asia/Manila")
                    ),
                    picture = PictureResponse(
                        "https://example.com/large.jpg",
                        "https://example.com/medium.jpg",
                        "https://example.com/thumbnail.jpg"
                    )
                )
            )
        ) { }
    }
}