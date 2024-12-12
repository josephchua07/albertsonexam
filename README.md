# Albertson User Directory

## Overview

This Android application displays a list of users fetched from an API and allows users to view details of a selected user. It is built using modern Android development practices, including Jetpack Compose, Hilt, and Coroutines.

## Features

* Displays a list of users with their names and profile pictures.
* Allows users to click on a user to view their detailed information.
* User details screen displays the user's full name, address, location coordinates, profile picture, and timezone information.
* Uses Jetpack Compose for a declarative UI.
* Utilizes Hilt for dependency injection.
* Uses Coroutines for asynchronous operations.
* Includes unit tests for the ViewModel.

## Dependencies

* Jetpack Compose
* Jetpack Navigation
* Hilt
* Coroutines
* Retrofit
* OkHttp
* MockK

## Architecture

The app follows a MVVM (Model-View-ViewModel) architecture

## Testing

Unit tests are provided for the `UsersViewModel` to ensure its correctness.
