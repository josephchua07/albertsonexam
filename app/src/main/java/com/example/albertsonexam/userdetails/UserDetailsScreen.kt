package com.example.albertsonexam.userdetails

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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.albertsonexam.R
import com.example.albertsonexam.ui.theme.AlbertsonexamTheme

@Composable
fun UserDetailsScreen(
    firstName: String,
    lastName: String,
    streetNumber: String,
    streetName: String,
    city: String,
    state: String,
    country: String,
    postcode: String,
    latitude: String,
    longitude: String,
    imageUrl: String,
    offset: String,
    description: String,
    modifier: Modifier = Modifier
) {

    Scaffold(modifier = modifier.fillMaxSize()) { paddingValues ->

        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 24.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_android_black_24dp)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(96.dp)
            )
            Text(text = "$firstName $lastName")
            Text(text = "$streetNumber $streetName")
            Text(text = "$city, $state")
            Text(text = "$country $postcode")
            Text(text = "$latitude, $longitude")
            Text(text = "$offset $description")
        }
    }

}

@Preview
@Composable
private fun UserDetailsScreenPreview() {
    AlbertsonexamTheme {
        UserDetailsScreen(
            firstName = "John",
            lastName = "Doe",
            streetNumber = "123",
            streetName = "Main St",
            city = "Paranaque",
            state = "Manila",
            country = "Philippines",
            postcode = "12345",
            latitude = "12.34",
            longitude = "56.78",
            imageUrl = "https://example.com/image.jpg",
            offset = "UTC+08:00",
            description = "Asia/Manila"
        )
    }
}