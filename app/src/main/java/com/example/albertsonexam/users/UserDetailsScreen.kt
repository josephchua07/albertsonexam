package com.example.albertsonexam.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.albertsonexam.R
import com.example.albertsonexam.data.models.UserResponse
import com.example.albertsonexam.ui.theme.AlbertsonexamTheme

@Composable
fun UserDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel()
) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->

        val selectedUser: UserResponse? = viewModel.selectedUser.value

        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 24.dp)
        ) {
            selectedUser?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it.picture.large)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_android_black_24dp)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(96.dp)
                )
                Text(text = "${it.name.title} ${it.name.first} ${it.name.last}")
                Text(text = it.gender)
                Text(text = it.email)
                Text(text = "Address:")
                Text(text = "${it.location.street.number} ${it.location.street.name}")
                Text(text = "${it.location.city}, ${it.location.state}")
                Text(text = "${it.location.country} ${it.location.postcode}")
                Text(text = "${it.location.coordinates.latitude}, ${it.location.coordinates.longitude}")
                Text(text = "${it.location.timezone.offset} ${it.location.timezone.description}")
            }
        }
    }
}

@Preview
@Composable
private fun UserDetailsScreenPreview() {
    AlbertsonexamTheme {
        UserDetailsScreen()
    }
}